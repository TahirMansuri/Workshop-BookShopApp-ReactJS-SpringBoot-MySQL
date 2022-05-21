package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.entity.UserBookEntity;
import com.infogalaxy.workshopbookstoreapp.responses.Response;

import java.util.List;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 20-05-2022
 */
public interface ICartService {
    //Function for Adding and Removing Book in/from User Cart
    boolean isBookAddedToCart(String token, long bookId);

    //Function to get All Cart List Books
    List<UserBookEntity> getCartList(String token);

    //Fundtion to Adding Book in Wish List
    Response isUserBookAddedToWishList(String token, long bookId);

    //Function to Get List of Wish List Books
    List<UserBookEntity> getWishList(String token);
}
