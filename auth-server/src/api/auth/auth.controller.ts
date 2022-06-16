import {
  Controller,
  Get,
  HttpException,
  HttpStatus,
  Param,
  Post,
  Query,
  Req,
  Res,
  Session,
} from '@nestjs/common';
import { Request, Response } from 'express';
import { AuthService } from './auth.service';
import { ApiParam, ApiQuery } from '@nestjs/swagger';

@Controller('auth')
export class AuthController {
  constructor(private authService: AuthService) { }

  @ApiQuery({ name: 'successUrl', type: 'string' })
  @Get('login')
  async renderLogin(
    @Session() session,
    @Req() req: Request,
    @Res() res: Response,
    @Query() query,
  ) {
    const { successUrl, hasError } = query;
    if (!successUrl) {
      return res.json({ message: 'successUrl query parameter needed' });
    }
    try {
      await this.authService.verifyToken(session.user);
      const url = new URL(successUrl);
      url.searchParams.append('token', session.user);
      return res.redirect(url.toString());
    } catch (e) {
      session.destroy();
      return res.render('login.hbs', { hasError: hasError });
    }
  }

  @ApiQuery({ name: 'successUrl', type: 'string' })
  @Get('logout')
  async logout(@Res() res: Response, @Query() query, @Session() session) {
    const { successUrl } = query;
    if (!successUrl) {
      return res.json({ message: 'successUrl query parameter needed' });
    }
    this.authService.clearUserTokensById(session.user_id);
    session.destroy();
    return res.redirect(successUrl);
  }

  @ApiQuery({ name: 'successUrl', type: 'string' })
  @Post('login')
  async login(
    @Req() req: Request,
    @Res() res: Response,
    @Query() query,
    @Session() session,
  ) {
    const { successUrl } = query;
    if (!successUrl) {
      return res.json({ message: 'successUrl query parameter needed' });
    }
    const user = await this.authService.validateUser(
      req.body.username,
      req.body.password,
    );
    if (!user) {
      return res.redirect(`login?successUrl=${successUrl}&hasError=true`);
    }
    const url = new URL(successUrl);
    const jwt = await this.authService.jwt(user);
    session.user_id = user._id;
    session.user = jwt;
    url.searchParams.append('token', jwt);
    return res.redirect(url.toString());
  }

  @Get('verify_token/:token')
  async verifyToken(@Param('token') token: string) {
    try {
      const jwtToken = await this.authService.verifyToken(token);
      const user = await this.authService.findUserByUsername(
        jwtToken.username as string,
      );
      if (!user.tokens?.includes(token)) throw Error("Token not persistent")
      return {
        claims: jwtToken,
        user: user,
      };
    } catch (e) {
      throw new HttpException(
        { status: HttpStatus.UNAUTHORIZED, error: 'Invalid token' },
        HttpStatus.UNAUTHORIZED,
      );
    }
  }
}
