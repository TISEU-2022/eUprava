import { Body, Controller, Get, Param, Post, Put, Delete } from '@nestjs/common';
import { UserService } from './user.service';
import { UserCreateDto, UserUpdateDto } from './user.dto';

@Controller('user')
export class UserController {
  constructor(private readonly service: UserService) {}

  @Post()
  async createUser(@Body() user: UserCreateDto) {
    await this.service.createUser(user);
  }

  @Get('find/username/:username')
  async findByUsername(@Param('username') username: string) {
    return await this.service.findByUsername(username);
  }

  @Get('find/role/:role')
  async findByRole(@Param('role') role: string) {
    return await this.service.findByRole(role);
  }

  @Get('find/id/:id')
  async findById(@Param('id') id: string) {
    return await this.service.findById(id);
  }

  @Get('find/identity_number/:identity_number')
  async findByIdentityNumber(@Param('identity_number') identityNumber: string) {
    return await this.service.findByIdentityNumber(identityNumber);
  }

  @Put('/:id')
  async updateUserById(@Param('id') id: string, @Body() user: UserUpdateDto) {
    return await this.service.updateUserById(id, user);
  }

  @Delete('/:id')
  async deleteUserById(@Param('id') id: string) {
    return await this.service.deleteUserById(id);
  }
}
