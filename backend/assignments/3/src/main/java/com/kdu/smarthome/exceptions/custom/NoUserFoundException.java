package com.kdu.smarthome.exceptions.custom;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException(String message) {
        super(message);
    }
}