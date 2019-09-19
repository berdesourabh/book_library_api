package com.books.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String BOOK_NOT_FOUND_CODE = "001";
    private static final String INVALID_BOOK_CODE = "002";
    private static final String READER_NOT_FOUND_CODE = "003";
    private static final String INVALID_READER_CODE = "004";


    @ExceptionHandler(BookNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {

        ErrorResponse error = new ErrorResponse(ex.getMessage(), BOOK_NOT_FOUND_CODE);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ReaderNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleReaderNotFoundException(ReaderNotFoundException ex, WebRequest request) {

        ErrorResponse error = new ErrorResponse(ex.getMessage(), READER_NOT_FOUND_CODE);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidBookException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidBookException(InvalidBookException ex, WebRequest request) {

        ErrorResponse error = new ErrorResponse(ex.getMessage(), INVALID_BOOK_CODE);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidReaderException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidReaderException(InvalidReaderException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), INVALID_READER_CODE);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

}
