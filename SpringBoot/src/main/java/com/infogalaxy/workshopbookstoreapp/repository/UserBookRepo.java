package com.infogalaxy.workshopbookstoreapp.repository;

import com.infogalaxy.workshopbookstoreapp.entity.UserBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 20-05-2022
 */
public interface UserBookRepo extends JpaRepository<UserBookEntity,Long> {
    UserBookEntity findUserBookEntityByBookCode(String bookCode);
}
