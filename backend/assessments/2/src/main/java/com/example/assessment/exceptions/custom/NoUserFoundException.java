package com.example.assessment.exceptions.custom;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException(String message) {
        super(message);
    }
}