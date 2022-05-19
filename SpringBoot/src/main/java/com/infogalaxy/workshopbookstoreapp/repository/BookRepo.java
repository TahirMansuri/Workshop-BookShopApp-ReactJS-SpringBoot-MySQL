package com.infogalaxy.workshopbookstoreapp.repository;

import com.infogalaxy.workshopbookstoreapp.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */
public interface BookRepo extends JpaRepository<BookEntity,Long> {
}
