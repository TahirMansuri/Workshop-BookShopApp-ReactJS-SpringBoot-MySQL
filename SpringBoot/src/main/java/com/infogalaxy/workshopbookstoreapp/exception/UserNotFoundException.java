package com.infogalaxy.workshopbookstoreapp.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BookStoreAppException{

    public UserNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
