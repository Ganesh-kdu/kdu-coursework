package com.example.handson4.exception.custom;

import java.util.NoSuchElementException;

public class ObjectNotFoundException extends NoSuchElementException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
