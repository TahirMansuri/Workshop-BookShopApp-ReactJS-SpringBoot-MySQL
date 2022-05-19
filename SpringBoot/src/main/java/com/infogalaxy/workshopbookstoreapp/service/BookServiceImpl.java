package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.dto.BookDTO;
import com.infogalaxy.workshopbookstoreapp.entity.BookEntity;
import com.infogalaxy.workshopbookstoreapp.entity.UserEntity;
import com.infogalaxy.workshopbookstoreapp.exception.UserNotFoundException;
import com.infogalaxy.workshopbookstoreapp.repository.BookRepo;
import com.infogalaxy.workshopbookstoreapp.repository.UserRepo;
import com.infogalaxy.workshopbookstoreapp.security.JWTTokenUtil;
import com.infogalaxy.workshopbookstoreapp.utility.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */
@Service
public class BookServiceImpl implements IBookService{

    @Autowired
    JWTTokenUtil jwtTokenUtil;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BookRepo bookRepo;

    //Check for the Admin Authentication by Username
    Optional<UserEntity> getAuthenticateUser(final String token) {
        return userRepo.findUserEntityByUsername(jwtTokenUtil.getUsername(token));
    }

    //Get Admin Data and Check for the Role
    boolean isUserAdmin(final String token) {
        Optional<UserEntity> fetchedUser = getAuthenticateUser(token);
        if(fetchedUser.isPresent()){
            return fetchedUser.get().getRole().contains(Util.ROLE_ADMIN);
        }
        throw new UserNotFoundException(Util.USER_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND);
    }

    //If User Token Verified and User Role is Admin then Proceed for Adding Book to Store
    @Override
    public boolean isBookAdded(BookDTO bookDTO, String token) {
        if(isUserAdmin(token)){
            BookEntity bookEntity = new BookEntity();
            BeanUtils.copyProperties(bookDTO,bookEntity);
            bookEntity.setBookCode(Util.randomIdGenerator());
            bookEntity.setAddDate(LocalDate.now());
            bookRepo.save(bookEntity);
            return true;
        }
        return false;
    }

    //This function return all the Book Data from Store
    @Override
    public List<BookEntity> getAllBooks(String token) {
        return bookRepo.findAll();
    }
}
