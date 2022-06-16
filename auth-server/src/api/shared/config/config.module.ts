import { Module } from '@nestjs/common';
import { ConfigService } from './config.service';
import * as path from 'path';

@Module({
  providers: [
    {
      provide: ConfigService,
      useValue: new ConfigService(
        (function () {
          console.log(
            'setting up config service with env:',
            process.env.NODE_ENV,
          );
          return path.resolve(
            __dirname,
            '..',
            '..',
            '..',
            '..',
            `.env.${process.env.NODE_ENV}`,
          );
        })(),
      ),
    },
  ],
  exports: [ConfigService],
})
export class ConfigModule {}
