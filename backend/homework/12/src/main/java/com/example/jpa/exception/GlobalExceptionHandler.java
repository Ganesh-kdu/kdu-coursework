package com.example.jpa.exception;

import com.example.jpa.dto.ErrorDTO;
import com.example.jpa.exception.custom.MyCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<ErrorDTO> handleCustomException(MyCustomException ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleAllOtherException(Exception ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
