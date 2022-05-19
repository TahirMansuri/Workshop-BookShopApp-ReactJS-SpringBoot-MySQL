package com.infogalaxy.workshopbookstoreapp.controller;

import com.infogalaxy.workshopbookstoreapp.dto.BookDTO;
import com.infogalaxy.workshopbookstoreapp.responses.Response;
import com.infogalaxy.workshopbookstoreapp.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */
@RestController
@RequestMapping("/bookstoreapp/api/book")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    /***
     *
     * @return
     */
    @RequestMapping(value = {"","/","home"})
    public String home(){
        return "<h1> Welcome to Book Store App - Book Module </h1>";
    }

    /***
     *
     * @param bookDTO
     * @param token
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Response> add(@RequestBody BookDTO bookDTO,@RequestHeader("token") final String token) {
        if(bookService.isBookAdded(bookDTO,token)){
            return new ResponseEntity<>(new Response("Book Added Succesfully.", 201),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new Response("Sorry... Problem in Adding Book.", 400), HttpStatus.BAD_REQUEST);
    }

    /***
     *
     * @param token
     * @return
     */
    @GetMapping("/get")
    public ResponseEntity<Response> get(@RequestHeader("token") String token) {
        return new ResponseEntity<Response>(new Response("BookList",HttpStatus.OK,bookService.getAllBooks(token)),HttpStatus.OK);
    }
}
