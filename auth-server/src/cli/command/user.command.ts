import { Injectable } from '@nestjs/common';
import * as bcrypt from 'bcrypt';
import { UserService } from '../../api/user/user.service';
import { Command, Positional } from 'nestjs-command';

@Injectable()
export class UserCommand {
  constructor(private readonly userService: UserService) {}

  @Command({
    command: 'seed:admin',
    describe: 'seed admin into db',
  })
  async seedAdmin() {
    const res = await this.userService.createUser({
      username: 'administrator',
      password: 'Password123',
      identityNumber: '12345678',
      roles: ['admin'],
      firstName: 'Admin',
      lastName: 'Admin',
    });
    console.log(res);
  }

  @Command({
    command:
      'seed:user <username> <password> <roles> <firstName> <lastName> <identityNumber>',
    describe: 'seed the user',
  })
  async seedUser(
    @Positional({
      name: 'username',
      describe: 'the username',
      type: 'string',
    })
    username: string,
    @Positional({
      name: 'password',
      describe: 'the password',
      type: 'string',
    })
    password: string,
    @Positional({
      name: 'roles',
      describe: 'the roles (separated by ,)',
      type: 'string',
    })
    roles: string,
    @Positional({
      name: 'firstName',
      describe: 'the first name',
      type: 'string',
    })
    firstName: string,
    @Positional({
      name: 'lastName',
      describe: 'the last name',
      type: 'string',
    })
    lastName: string,
    @Positional({
      name: 'identityNumber',
      describe: 'the identity number',
      type: 'string',
    })
    identityNumber: string,
  ) {
    const res = await this.userService.createUser({
      username,
      roles: roles.split(','),
      firstName,
      password,
      lastName,
      identityNumber,
    });
    console.log(res);
  }
}
