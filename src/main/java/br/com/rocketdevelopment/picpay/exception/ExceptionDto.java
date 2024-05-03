package br.com.rocketdevelopment.picpay.exception;

import org.springframework.http.HttpStatus;

public record ExceptionDto(HttpStatus httpStatus, String message) {

}
