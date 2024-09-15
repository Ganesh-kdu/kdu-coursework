package com.example.jpa.exception.custom;

public class MyCustomException extends RuntimeException {
    public MyCustomException(String message) {
        super(message);
    }
}
