package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.dto.CustomerRegisterDTO;
import com.infogalaxy.workshopbookstoreapp.entity.CustomerRegisterEntity;

public interface ICustomerService {

    /***
     * Service to Add Customer to App
     */
    boolean addCustomer(CustomerRegisterDTO customerRegisterDTO);

}
