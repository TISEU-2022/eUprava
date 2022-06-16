import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { UserModule } from './api/user/user.module';
import { AuthModule } from './api/auth/auth.module';
import { SharedModule } from './api/shared/shared.module';
import { MongooseModule } from '@nestjs/mongoose';
import { MongooseConfigService } from './api/shared/mongoose-config/mongoose-config.service';
import { ConfigModule } from './api/shared/config/config.module';
import { CommandModule } from 'nestjs-command';
import { UserCommand } from './cli/command/user.command';

@Module({
  imports: [
    CommandModule,
    UserModule,
    AuthModule,
    SharedModule,
    ConfigModule,
    MongooseModule.forRootAsync({
      imports: [ConfigModule],
      useClass: MongooseConfigService,
    }),
  ],
  controllers: [AppController],
  providers: [AppService, UserCommand],
})
export class AppModule {}
