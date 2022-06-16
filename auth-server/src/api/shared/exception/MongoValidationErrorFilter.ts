import { ArgumentsHost, Catch, ExceptionFilter } from '@nestjs/common';
import { MongoServerError } from 'mongodb';

import { Error } from 'mongoose';

import ValidationError = Error.ValidationError;

@Catch(ValidationError)
export class MongoValidationErrorFilter implements ExceptionFilter {
  catch(exception: ValidationError, host: ArgumentsHost): any {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse();
    return response.status(400).json({
      statusCode: 400,
      createdBy: 'MongoValidationErrorFilter',
      errors: exception.errors,
      message: exception.message,
      name: exception.name,
    });
  }
}
