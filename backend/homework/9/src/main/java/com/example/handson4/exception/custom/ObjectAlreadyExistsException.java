package com.example.handson4.exception.custom;

public class ObjectAlreadyExistsException extends IllegalArgumentException{
    public ObjectAlreadyExistsException(String message) {
        super(message);
    }
}
