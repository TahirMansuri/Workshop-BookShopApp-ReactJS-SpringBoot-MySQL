package com.infogalaxy.workshopbookstoreapp.service;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 20-05-2022
 */
public interface ICartService {
    boolean isBookAddedToCart(String token, long bookId);
}
