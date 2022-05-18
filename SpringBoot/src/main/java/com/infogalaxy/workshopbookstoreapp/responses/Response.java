package com.infogalaxy.workshopbookstoreapp.responses;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {

    private String message;
    private HttpStatus httpStatus;
    private Object object;
    private int statusCode;

    public Response(String message) {
        this.message = message;
    }

    public Response(String message,int statusCode) {
        //this.message = message;
        this( message );
        this.statusCode = statusCode;
    }

    public Response(String message, HttpStatus httpStatus){
        this(message);
        this.httpStatus = httpStatus;
    }

    public Response(String message, HttpStatus httpStatus, Object object) {
        this(message,httpStatus);
        this.object = object;
    }
}
