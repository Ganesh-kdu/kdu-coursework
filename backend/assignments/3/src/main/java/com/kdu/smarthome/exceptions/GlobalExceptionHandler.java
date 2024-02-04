package com.kdu.smarthome.exceptions;


import com.kdu.smarthome.dto.responses.ErrorDto;
import com.kdu.smarthome.exceptions.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNoUserFoundException(NotFoundException exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalCallerException.class)
    public ResponseEntity<ErrorDto> handleException(IllegalCallerException exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}