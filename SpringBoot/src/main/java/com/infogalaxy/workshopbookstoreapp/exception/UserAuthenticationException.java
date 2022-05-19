package com.infogalaxy.workshopbookstoreapp.exception;

import org.springframework.http.HttpStatus;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */
public class UserAuthenticationException extends BookStoreAppException{
    public UserAuthenticationException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
