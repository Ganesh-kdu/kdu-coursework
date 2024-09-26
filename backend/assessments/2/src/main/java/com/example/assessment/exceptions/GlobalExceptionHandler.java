package com.example.assessment.exceptions;

import com.example.assessment.dto.ErrorDto;
import com.example.assessment.exceptions.custom.NoUserFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<ErrorDto> handleNoUserFoundException(NoUserFoundException exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}