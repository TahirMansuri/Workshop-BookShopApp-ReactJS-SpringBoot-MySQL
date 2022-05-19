package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.dto.BookDTO;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */
public interface IBookService {
    boolean isBookAdded(BookDTO bookDTO, String token);
}
