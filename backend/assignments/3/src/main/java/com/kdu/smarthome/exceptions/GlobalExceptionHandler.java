package com.kdu.smarthome.exceptions;


import com.kdu.smarthome.dto.responses.ErrorDto;
import com.kdu.smarthome.exceptions.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
/**
 * Handler to catch thrown errors and return custom response
 */
public class GlobalExceptionHandler {
    /**
     * Thrown when a particular user or house doesn't exist in database
     * @param exception Exception with message
     * @return Response entity with HTTP Bad request 400
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNoUserFoundException(NotFoundException exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Thrown when an action is attempted by a user not authorized for that action
     * @param exception Exception with message
     * @return Response entity with HTTP Unauthorized 401
     */
    @ExceptionHandler(IllegalCallerException.class)
    public ResponseEntity<ErrorDto> handleException(IllegalCallerException exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}