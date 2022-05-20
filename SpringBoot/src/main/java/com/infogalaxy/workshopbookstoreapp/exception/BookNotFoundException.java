package com.infogalaxy.workshopbookstoreapp.exception;

import org.springframework.http.HttpStatus;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 20-05-2022
 */
public class BookNotFoundException extends BookStoreAppException{
    public BookNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
