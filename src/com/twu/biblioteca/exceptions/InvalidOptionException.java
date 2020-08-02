package com.twu.biblioteca.exceptions;

// 将Exception提取出来做的很好!
public class InvalidOptionException extends RuntimeException {

    public InvalidOptionException() {
    }

    public InvalidOptionException(String message) {
        super(message);
    }
}
