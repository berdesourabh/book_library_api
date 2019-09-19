package com.books.app.exception;

public class ErrorResponse {

    private String message;
    private String code;

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }


}
