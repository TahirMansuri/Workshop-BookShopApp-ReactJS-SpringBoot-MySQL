package com.infogalaxy.workshopbookstoreapp.repository;

import com.infogalaxy.workshopbookstoreapp.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 21-05-2022
 */
public interface AddressRepo extends JpaRepository<AddressEntity,Long> {
}
