package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.dto.BookDTO;
import com.infogalaxy.workshopbookstoreapp.entity.BookEntity;

import java.util.List;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */
public interface IBookService {
    /***
     * This function is Adding Book to Store by Checking Admin Credentials and Token Validation
     * @param bookDTO
     * @param token
     * @return
     */
    boolean isBookAdded(BookDTO bookDTO, String token);

    /***
     * This function is Getting All Books List from Store with Admin Token Validation
     * @param token
     * @return
     */
    List<BookEntity> getAllBooks(String token);
}
