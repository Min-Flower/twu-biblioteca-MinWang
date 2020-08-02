package com.twu.biblioteca.exceptions;

public class InvalidBookException extends RuntimeException {

    public InvalidBookException() {
    }

    public InvalidBookException(String message) {
        super(message);
    }
}
