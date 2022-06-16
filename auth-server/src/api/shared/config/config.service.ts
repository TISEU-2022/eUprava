import * as dotenv from 'dotenv';
import * as fs from 'fs';

export class ConfigService {
  private readonly envConfig: { [key: string]: string };

  constructor(filePath: string) {
    this.envConfig = {
      NODE_ENV: process.env.NODE_ENV,
    };

    if (fs.existsSync(filePath)) {
      this.envConfig = {
        ...process.env,
        ...this.envConfig,
        ...dotenv.parse(fs.readFileSync(filePath)),
      };
    }

    this.envConfig = {
      ...this.envConfig,
      ...process.env,
    };
  }

  get(key: string): string {
    const retVal = this.envConfig[key];
    if (!retVal) throw new Error(`unable to parse ${key} from config service`);
    return retVal;
  }

  getOrNull(key: string): string | null {
    return this.envConfig[key] ?? null;
  }
}
