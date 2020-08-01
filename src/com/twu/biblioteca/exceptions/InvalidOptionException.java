package com.twu.biblioteca.exceptions;

public class InvalidOptionException extends RuntimeException {

    public InvalidOptionException() {
    }

    public InvalidOptionException(String message) {
        super(message);
    }
}
