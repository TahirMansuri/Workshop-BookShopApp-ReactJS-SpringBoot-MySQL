package com.infogalaxy.workshopbookstoreapp.controller;

import com.infogalaxy.workshopbookstoreapp.responses.Response;
import com.infogalaxy.workshopbookstoreapp.service.CartServiceImpl;
import org.apache.catalina.filters.RestCsrfPreventionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 20-05-2022
 */
@RestController
@RequestMapping("/bookstoreapp/api/cart")
public class CartController {

    @Autowired
    CartServiceImpl cartService;

    @RequestMapping(value = {"","/","/home"})
    public String home() {
        return "<h1> Welcome to Book Store App - Cart Module </h1>";
    }

    /***
     *
     * @param token
     * @param bookId
     * @return
     */
    @PostMapping("/cart")
    public ResponseEntity<Response> addOrRemoveFromCart(@RequestHeader("token") String token, @RequestParam("serialNumber") long bookId) {
        boolean isBookAddedToCart = cartService.isBookAddedToCart(token,bookId);
        if(isBookAddedToCart) {
            return new ResponseEntity<Response>(new Response("Book Added to Cart.",200), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response("Book Removed from Cart",202),HttpStatus.ACCEPTED);
    }

    /***
     *
     * @param token
     * @return
     */
    @GetMapping("/cart")
    public ResponseEntity<Response> getCartList(@RequestHeader("token") String token) {
        return new ResponseEntity<>(new Response("Cart List :",HttpStatus.OK,cartService.getCartList(token)),HttpStatus.OK);
    }

    /***
     *
     * @param token
     * @param bookId
     * @return
     */
    @PutMapping("/wishlist")
    public ResponseEntity<Response> addOrRemoveFromWishList(@RequestHeader("token") String token,@RequestParam("bookId") long bookId) {
        return new ResponseEntity<Response>(cartService.isUserBookAddedToWishList(token,bookId),HttpStatus.OK);
    }

    /***
     *
     * @param token
     * @return
     */
    @GetMapping("/wishlist")
    public ResponseEntity<Response> getWishList(@RequestHeader("token") String token) {
        return new ResponseEntity<>(new Response("WishList :",HttpStatus.OK,cartService.getWishList(token)),HttpStatus.OK);
    }
}
