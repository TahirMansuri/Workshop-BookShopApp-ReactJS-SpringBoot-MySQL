package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.dto.CustomerRegisterDTO;
import com.infogalaxy.workshopbookstoreapp.entity.CustomerRegisterEntity;

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
public interface ICustomerService {

    /***
     * Service to Add Customer to App
     */
    boolean addCustomer(CustomerRegisterDTO customerRegisterDTO);

    boolean updateCustomer(CustomerRegisterDTO customerRegisterDTO, long id);

    boolean deleteCustomer(long id);
}
