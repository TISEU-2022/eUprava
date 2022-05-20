package ftn.euprava.mupvozila.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e){

        HttpStatus status = HttpStatus.NOT_FOUND;

        ExceptionMessage exceptionMessage = new ExceptionMessage(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionMessage, status);
    }
}
