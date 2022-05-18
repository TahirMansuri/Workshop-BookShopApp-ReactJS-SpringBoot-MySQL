package com.infogalaxy.workshopbookstoreapp.exception;

import org.springframework.http.HttpStatus;

public class MailSendingException extends BookStoreAppException{
    public MailSendingException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
