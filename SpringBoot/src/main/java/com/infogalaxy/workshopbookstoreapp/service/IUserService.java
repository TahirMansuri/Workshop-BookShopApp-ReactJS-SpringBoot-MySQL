package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.dto.AddressDTO;
import com.infogalaxy.workshopbookstoreapp.dto.UserDTO;
import com.infogalaxy.workshopbookstoreapp.dto.LoginDTO;
import com.infogalaxy.workshopbookstoreapp.entity.AddressEntity;
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

    /***
     * Service to Update User Data
     * @param customerRegisterDTO
     * @param id
     * @return
     */
    boolean updateCustomer(UserDTO customerRegisterDTO, long id);

    /***
     * Service to Delete User from System
     * @param id
     * @return
     */
    boolean deleteCustomer(long id);

    /***
     * Service to List all available user to system
     * @return
     */
    List<UserEntity> readAllCustomers();

    /***
     * Service to Verify User by OTP
     * @param token
     * @return
     */
    boolean isVerifiedUser(String token);

    /***
     * Service to Perform User Login by Username and Password
     * @param loginDTO
     * @return
     */
    UserLoginInfo login(LoginDTO loginDTO);

    /***
     * Service to Add Address for User
     * @param addressDTO
     * @param token
     * @return
     */
    boolean isUserAddressAdded(AddressDTO addressDTO,String token);

    /***
     * Service to Get All Available address of Login User
     * @param token
     * @return
     */
    List<AddressEntity> getUserAllAddress(String token);
}
