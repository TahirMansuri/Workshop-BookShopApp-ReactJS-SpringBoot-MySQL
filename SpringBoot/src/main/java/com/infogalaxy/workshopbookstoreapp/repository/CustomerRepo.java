package com.infogalaxy.workshopbookstoreapp.repository;

import com.infogalaxy.workshopbookstoreapp.entity.CustomerRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<CustomerRegisterEntity, Long> {

    Optional<CustomerRegisterEntity> findCustomerRegisterEntityByUsername(final String username);

    boolean existsByEmailId( final String emailId );

    CustomerRegisterEntity findById(long id);

    @Modifying
    @Transactional
    @Query(value = " update customer set verify = true where username =  ? ", nativeQuery = true)
    void verifyCustomer(String username);
}
