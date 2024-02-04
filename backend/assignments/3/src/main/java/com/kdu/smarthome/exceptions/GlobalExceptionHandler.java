package com.kdu.smarthome.exceptions;


import com.kdu.smarthome.dto.ErrorDto;
import com.kdu.smarthome.exceptions.custom.NoUserFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<ErrorDto> handleNoUserFoundException(NoUserFoundException exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalCallerException.class)
    public ResponseEntity<ErrorDto> handleException(IllegalCallerException exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler(NoUserFoundException.class)
//    public ResponseEntity<ErrorDto> handleNoUser(NoUserFoundException exception){
//        return new ResponseEntity<>(new ErrorDto(exception.getMessage()),HttpStatus.BAD_REQUEST);
//    }
}