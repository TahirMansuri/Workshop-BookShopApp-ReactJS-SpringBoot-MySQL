package com.infogalaxy.workshopbookstoreapp.repository;

import com.infogalaxy.workshopbookstoreapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByUsername(final String username);

    boolean existsByEmailId( final String emailId );

    UserEntity findById(long id);

    @Modifying
    @Transactional
    @Query(value = " update userdata set verify = 1 where verification_code =  ? ", nativeQuery = true)
    void verifyUser(String username);
}
