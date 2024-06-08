package br.com.rocketdevelopment.picpay.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ValidadeException.class)
    public ResponseEntity<ExceptionDto> NotFoundException(ValidadeException e){
        log.atLevel(Level.ERROR).log(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
    }
    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ExceptionDto> NotFoundException(TransactionException e){
        log.atLevel(Level.ERROR).log(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
    }
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionDto> NotFoundException(UserException e){
        log.atLevel(Level.ERROR).log(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
    }
}
