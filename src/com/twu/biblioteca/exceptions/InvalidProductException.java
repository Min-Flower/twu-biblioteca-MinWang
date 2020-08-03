package com.twu.biblioteca.exceptions;

public class InvalidProductException extends RuntimeException {

    public InvalidProductException() {
    }

    public InvalidProductException(String message) {
        super(message);
    }
}
