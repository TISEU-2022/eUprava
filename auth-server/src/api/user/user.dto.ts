import { IsArray, IsNotEmpty, IsOptional } from 'class-validator';
import { Type } from 'class-transformer';
import { ApiProperty } from '@nestjs/swagger';
import { Optional } from '@nestjs/common';

export class UserCreateDto {
  @ApiProperty()
  @IsNotEmpty()
  @IsOptional()
  @Type(() => String)
  username: string;

  @ApiProperty()
  @IsOptional()
  @IsNotEmpty()
  @Type(() => String)
  firstName: string;

  @ApiProperty()
  @IsOptional()
  @IsNotEmpty()
  @Type(() => String)
  lastName: string;

  @ApiProperty()
  @IsOptional()
  @IsNotEmpty()
  @Type(() => String)
  password: string;

  @ApiProperty()
  @IsOptional()
  @IsNotEmpty()
  @Type(() => String)
  identityNumber: string;

  @ApiProperty()
  @IsOptional()
  @IsArray()
  roles: string[];
}

export class UserUpdateDto {
  @ApiProperty()
  @Type(() => String)
  username: string;

  @ApiProperty()
  @IsOptional()
  @Type(() => String)
  firstName: string;

  @ApiProperty()
  @IsOptional()
  @Type(() => String)
  lastName: string;

  @ApiProperty()
  @IsOptional()
  @Type(() => String)
  password: string;

  @ApiProperty()
  @IsOptional()
  @Type(() => String)
  identityNumber: string;

  @ApiProperty()
  @IsOptional()
  @IsArray()
  roles: string[];
}
