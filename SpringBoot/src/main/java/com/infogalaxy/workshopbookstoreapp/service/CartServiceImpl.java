package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.entity.AdminBookEntity;
import com.infogalaxy.workshopbookstoreapp.entity.UserBookEntity;
import com.infogalaxy.workshopbookstoreapp.entity.UserEntity;
import com.infogalaxy.workshopbookstoreapp.exception.BookNotFoundException;
import com.infogalaxy.workshopbookstoreapp.repository.BookRepo;
import com.infogalaxy.workshopbookstoreapp.repository.UserBookRepo;
import com.infogalaxy.workshopbookstoreapp.repository.UserRepo;
import com.infogalaxy.workshopbookstoreapp.responses.Response;
import com.infogalaxy.workshopbookstoreapp.security.JWTTokenUtil;
import com.infogalaxy.workshopbookstoreapp.utility.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 20-05-2022
 */
@Service
public class CartServiceImpl implements ICartService{

    @Autowired
    BookRepo bookRepo;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    UserBookRepo userBookRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JWTTokenUtil jwtTokenUtil;

    /***
     * This functionality Add Selected Book to User Cart
     * @param token
     * @param bookId
     * @return
     */
    @Override
    public boolean isBookAddedToCart(String token, long bookId) {

        //Checking the Book Availability from Admin Side
        AdminBookEntity adminBookEntity = bookService.getBookById(bookId);

        //Get the User Info By Validating Token
        UserEntity userEntity = userService.getAuthenticateUserWithRoleUser(token);

        //Get User Cart for Selected Book if Present or Not
        UserBookEntity userBookEntity = checkAndCreateNewBookForUserAndCopyContent(adminBookEntity.getBookCode(),adminBookEntity);

        //If Book is Not Already Added to cart then Add Book To Cart and Update Status
        if(!userBookEntity.isAddedToCart()) {
            userBookEntity.setAddedToCart(true);
            userBookEntity.setUpdateDateTime(Util.currentDateTime());
            userEntity.getBooksList().add(userBookEntity);
            userBookRepo.save(userBookEntity);
            return true;
        }
        //Else if Book is Available in Cart then Remove the Book From Cart
        userBookEntity.setAddedToCart(false);
        userBookEntity.setUpdateDateTime(Util.currentDateTime());
        userEntity.getBooksList().remove(userBookEntity);
        userBookRepo.saveAndFlush(userBookEntity);
        return false;
    }

    @Override
    public List<UserBookEntity> getCartList(String token) {
        return userService.getAuthenticateUserWithRoleUser(token)
                .getBooksList()
                .stream()
                .filter(UserBookEntity::isAddedToCart)
                .collect(Collectors.toList());
    }

    /***
     * This functionality used for Adding Book to Wish List for User
     * @param token
     * @param bookId
     */
    @Override
    public Response isUserBookAddedToWishList(String token, long bookId) {
       Optional<AdminBookEntity> adminBookEntity = bookService.isValidBook(bookId);
       UserEntity userEntity = userService.getAuthenticateUserWithRoleUser(token);
       UserBookEntity userBookEntity = checkAndCreateNewBookForUserAndCopyContent(adminBookEntity.get().getBookCode(),adminBookEntity.get());
       if(!userBookEntity.isAddedToWishList()){
            userBookEntity.setAddedToWishList(true);
            userBookEntity.setUpdateDateTime(Util.currentDateTime());
            userEntity.getBooksList().add(userBookEntity);
            userBookRepo.saveAndFlush(userBookEntity);
            return new Response("Book Added to WishList",201);
       } else {
           return new Response("Book Not Added to WishList",HttpStatus.NOT_FOUND);
       }
    }
    /***
     * This fundtionality check for the Availability of Selected Book in Cart
     * If the Book is already in Cart then it return userBookEntity
     * else it will copy the Selected Book Properties to NewBookEntity object and return newBookEntity
     * @param bookCode
     * @param adminBookEntity
     * @return
     */
    private UserBookEntity checkAndCreateNewBookForUserAndCopyContent(String bookCode, AdminBookEntity adminBookEntity) {
        UserBookEntity userBookEntity = userBookRepo.findUserBookEntityByBookCode(bookCode);
        if(userBookEntity!=null) {
            return userBookEntity;
        }
        UserBookEntity newUserBookEntity = new UserBookEntity();
        BeanUtils.copyProperties(adminBookEntity,newUserBookEntity);
        newUserBookEntity.setBookId(0);
        return newUserBookEntity;
    }

    /***
     * Fundtionlity add the book to user wishlist
     * @param token
     * @return
     */
    @Override
    public List<UserBookEntity> getWishList(String token) {
        return userService.getAuthenticateUserWithRoleUser(token)
                .getBooksList()
                .stream()
                .filter(UserBookEntity :: isAddedToWishList)
                .collect(Collectors.toList());
    }

}
