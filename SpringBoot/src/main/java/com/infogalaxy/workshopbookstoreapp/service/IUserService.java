package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.dto.UserDTO;
import com.infogalaxy.workshopbookstoreapp.dto.LoginDTO;
import com.infogalaxy.workshopbookstoreapp.entity.UserEntity;

import java.util.List;

/***
 * This interface has unimplemented functionalities which are
 * used for registering the Customer to the system and also
 * validating the identity, login, forget password and update password
 * functionalities
 *
 * @author Tahir Mansuri
 * @version 1.0.0
 * @created 2022-05-16
 */
public interface IUserService {

    /***
     * Service to Add Customer to App
     */
    boolean addCustomer(UserDTO customerRegisterDTO);

    boolean updateCustomer(UserDTO customerRegisterDTO, long id);

    boolean deleteCustomer(long id);

    List<UserEntity> readAllCustomers();

    boolean isVerifiedUser(String token);

    UserLoginInfo login(LoginDTO loginDTO);
}
