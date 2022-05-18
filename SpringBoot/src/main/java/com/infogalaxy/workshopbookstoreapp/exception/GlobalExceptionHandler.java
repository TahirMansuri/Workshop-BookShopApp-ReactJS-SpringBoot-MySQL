package com.infogalaxy.workshopbookstoreapp.exception;

import com.infogalaxy.workshopbookstoreapp.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handleUserNotFound(UserNotFoundException e) {
        return new ResponseEntity<Response>(new Response(e.getMessage(),e.getHttpStatus()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MailSendingException.class)
    public ResponseEntity<Response> handleMailSendingException(MailSendingException e) {
          return new ResponseEntity<Response>(new Response(e.getMessage(),e.getHttpStatus()),HttpStatus.BAD_GATEWAY);
    }
}
