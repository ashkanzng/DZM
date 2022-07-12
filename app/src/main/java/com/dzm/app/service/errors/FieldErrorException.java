package com.dzm.app.service.errors;

public class FieldErrorException extends RuntimeException {
    public FieldErrorException(String message) {
        super(message);
    }
}
