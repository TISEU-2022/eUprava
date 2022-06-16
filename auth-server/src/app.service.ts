import { Injectable } from '@nestjs/common';

@Injectable()
export class AppService {
  getHello(): { code: string; message: string; status: number } {
    return { message: 'Hello World!', status: 200, code: 'OK' };
  }
}
