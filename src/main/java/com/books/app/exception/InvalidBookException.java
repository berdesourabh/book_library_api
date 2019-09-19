package com.books.app.exception;

public class InvalidBookException extends RuntimeException {

    public InvalidBookException(String message)
    {
        super(message);
    }
}
