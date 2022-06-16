import { ArgumentsHost, Catch, ExceptionFilter } from '@nestjs/common';
import { MongoServerError } from 'mongodb';

@Catch(MongoServerError)
export class MongoServerExceptionFilter implements ExceptionFilter {
  catch(exception: MongoServerError, host: ArgumentsHost): any {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse();
    return response.status(400).json({
      statusCode: 400,
      createdBy: 'MongoServerExceptionFilter',
      message: exception.message,
      name: exception.name,
      codeName: exception.codeName,
      code: exception.code,
      errInfo: exception.errInfo,
    });
  }
}
