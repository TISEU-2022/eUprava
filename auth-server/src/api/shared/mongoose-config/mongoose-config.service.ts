import { Injectable } from '@nestjs/common';
import {
  MongooseModuleOptions,
  MongooseOptionsFactory,
} from '@nestjs/mongoose';
import { ConfigService } from '../config/config.service';

@Injectable()
export class MongooseConfigService implements MongooseOptionsFactory {
  constructor(private readonly config: ConfigService) {}

  createMongooseOptions(): MongooseModuleOptions {
    const user = this.config.getOrNull('DB_USER');
    const pass = this.config.getOrNull('DB_PASS');
    const userAndPass =
      user && pass
        ? {
            user,
            pass,
          }
        : {};

    const options: MongooseModuleOptions = {
      ...userAndPass,
      uri: this.config.get('DB_URI'),
      dbName: this.config.get('DB_NAME'),
      useNewUrlParser: true,
      useUnifiedTopology: true,
    };

    const certFileName = this.config.getOrNull('DB_CERT');

    if (certFileName) {
      options.tlsCAFile = `${__dirname}/../../certs/${certFileName}`;
    }

    return options;
  }
}
