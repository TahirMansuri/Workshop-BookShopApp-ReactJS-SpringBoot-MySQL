package com.infogalaxy.workshopbookstoreapp.exception;

import org.springframework.http.HttpStatus;

/***
 * Custom Exception class for handling custom exception
 */
public class BookStoreAppException extends RuntimeException{

    private final HttpStatus httpStatus;

    public BookStoreAppException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
