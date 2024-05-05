package br.com.rocketdevelopment.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidadeException.class)
    public ResponseEntity<ExceptionDto> NotFoundException(ValidadeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
    }
    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ExceptionDto> NotFoundException(TransactionException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
    }
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionDto> NotFoundException(UserException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
    }
}
