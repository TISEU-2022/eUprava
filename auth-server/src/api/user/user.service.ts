import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { User } from './user.schema';
import { Model, Document, ObjectId, Types } from 'mongoose';
import { UserUpdateDto } from './user.dto';
import * as bcrypt from 'bcrypt';

@Injectable()
export class UserService {
  constructor(
    @InjectModel(User.name) private userModel: Model<User & Document>,
  ) { }

  async findByUsernameInternal(username: string): Promise<User> {
    return this.userModel
      .findOne({ username }, undefined, { lean: true })
      .exec();
  }

  async createUser(param: Omit<User, '_id' | 'tokens'>) {
    if (param.password) {
      param = Object.assign(param, {
        password: await this.hashPassword(param.password),
      });
    }
    return await this.userModel.create(param);
  }

  async findById(id: string) {
    if (!Types.ObjectId.isValid(id)) {
      throw new HttpException(
        `Id ${id} is not valid ObjectId`,
        HttpStatus.BAD_REQUEST,
      );
    }
    const ret = await this.userModel
      .findById(id, undefined, { lean: true })
      .exec();
    if (!ret) {
      throw new HttpException(
        `User by id ${id} not found`,
        HttpStatus.NOT_FOUND,
      );
    }
    delete ret.password;
    return ret;
  }

  async findByIdentityNumber(identityNumber: string) {
    const ret = await this.userModel
      .findOne({ identityNumber }, undefined, { lean: true })
      .exec();
    if (!ret) {
      throw new HttpException(
        `User by identityNumber ${identityNumber} not found`,
        HttpStatus.NOT_FOUND,
      );
    }
    delete ret.password;
    return ret;
  }

  async findByUsername(username: string) {
    const ret = await this.userModel
      .findOne({ username }, undefined, { lean: true })
      .exec();
    if (!ret) {
      throw new HttpException(
        `User by username ${username} not found`,
        HttpStatus.NOT_FOUND,
      );
    }
    delete ret.password;
    return ret;
  }

  async findByRole(role: string) {
    const ret = await this.userModel
      .find({ roles: role }, undefined, { lean: true })
      .exec();
    if (!ret) {
      throw new HttpException(
        `Users by role ${role} not found`,
        HttpStatus.NOT_FOUND,
      );
    }
    return ret.map((e) => ({ ...e, password: undefined }));
  }

  async updateUserById(id: string, user: UserUpdateDto) {
    if (!Types.ObjectId.isValid(id)) {
      throw new HttpException(
        `Id ${id} is not valid ObjectId`,
        HttpStatus.BAD_REQUEST,
      );
    }
    const foundUser = await this.userModel.findById(id).exec();
    if (user.password) {
      user = Object.assign(user, {
        password: await this.hashPassword(user.password),
      });
    }
    const res = await foundUser.update(user, { lean: true }).exec();
    delete res.password;
    return res;
  }

  async hashPassword(password: string) {
    return await bcrypt.hash(password, 12);
  }

  async deleteUserById(id: string) {
    if (!Types.ObjectId.isValid(id)) {
      throw new HttpException(
        `Id ${id} is not valid ObjectId`,
        HttpStatus.BAD_REQUEST,
      );
    }
    await this.userModel.findById(id).remove().exec();
  }

  async addTokenToUser(userId: string, token: string) {
    this.userModel.findByIdAndUpdate(userId, {
      $push: { tokens: token }
    }).exec()
  }
  async clearUserTokensById(id: string) {
    this.userModel.findByIdAndUpdate(id, { tokens: [] }).exec()
  }
}
