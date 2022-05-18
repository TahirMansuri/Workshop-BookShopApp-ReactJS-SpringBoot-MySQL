package com.infogalaxy.workshopbookstoreapp.repository;

import com.infogalaxy.workshopbookstoreapp.entity.CustomerRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<CustomerRegisterEntity, Long> {

    Optional<CustomerRegisterEntity> findCustomerRegisterEntityByEmailId(final String emailId);

    boolean existsByEmailId( final String emailId );

    CustomerRegisterEntity findById(long id);
}
