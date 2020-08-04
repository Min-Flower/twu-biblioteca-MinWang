package com.twu.biblioteca.exceptions;

public class FailToVerifyException extends RuntimeException {

    public FailToVerifyException() {
    }

    public FailToVerifyException(String message) {
        super(message);
    }
}
