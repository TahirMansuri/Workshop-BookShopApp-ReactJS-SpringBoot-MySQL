package com.infogalaxy.workshopbookstoreapp.repository;

import com.infogalaxy.workshopbookstoreapp.entity.AdminBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */
public interface BookRepo extends JpaRepository<AdminBookEntity,Long> {
    //AdminBookEntity findAdminBookEntityByCode(long bookId);
    AdminBookEntity findAdminBookEntityByBookCode(long bookCode);
    //AdminBookEntity findAdminBookEntityById(long bookId);
}
