package com.example.handson4.exception;

import com.example.handson4.dto.ErrorDto;
import com.example.handson4.exception.custom.ObjectAlreadyExistsException;
import com.example.handson4.exception.custom.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends Exception{
    @ExceptionHandler(value = {ObjectNotFoundException.class})
    public ResponseEntity<ErrorDto> handleObjectNotFoundException(ObjectNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ObjectAlreadyExistsException.class})
    public ResponseEntity<ErrorDto> handleObjectAlreadyExistsException(ObjectAlreadyExistsException exception) {
        ErrorDto errorDto = new ErrorDto(exception.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDto> handleAllOtherExceptions(Exception exception) {
        ErrorDto errorDto = new ErrorDto(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}


