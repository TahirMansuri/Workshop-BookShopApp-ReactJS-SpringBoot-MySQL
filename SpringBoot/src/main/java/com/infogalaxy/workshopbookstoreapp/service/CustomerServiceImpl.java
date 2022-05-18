package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.dto.CustomerRegisterDTO;
import com.infogalaxy.workshopbookstoreapp.entity.CustomerRegisterEntity;
import com.infogalaxy.workshopbookstoreapp.exception.BookStoreAppException;
import com.infogalaxy.workshopbookstoreapp.exception.UserNotFoundException;
import com.infogalaxy.workshopbookstoreapp.repository.CustomerRepo;
import com.infogalaxy.workshopbookstoreapp.util.JMSUtil;
import com.infogalaxy.workshopbookstoreapp.utility.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JMSUtil jmsUtil;
    /***
     * Service Implementation for Add Customer to App using BookStoreApp Repository
     * @param customerRegisterDTO
     * @return
     */
    @Override
    public boolean addCustomer(CustomerRegisterDTO customerRegisterDTO) {
        if(!customerRepo.existsByEmailId(customerRegisterDTO.getEmailId())) {
            CustomerRegisterEntity customerRegisterEntity = new CustomerRegisterEntity();
            BeanUtils.copyProperties(customerRegisterDTO,customerRegisterEntity);
            customerRegisterEntity.setPassword(passwordEncoder.encode(customerRegisterDTO.getPassword()));
            customerRegisterEntity.setVerificationCode(Util.generateOTP());
            customerRegisterEntity.setRegisterDate(LocalDate.now());
            customerRegisterEntity.setVerify(false);
            customerRepo.save(customerRegisterEntity);

            Optional<CustomerRegisterEntity> fetchCustData = customerRepo.findCustomerRegisterEntityByEmailId(customerRegisterEntity.getEmailId());
            if(fetchCustData.isPresent()) {
                jmsUtil.sendMail(customerRegisterDTO.getEmailId(),"Verification Code for Book Store App","Hello "+customerRegisterDTO.getFirstName()+", Verification Code for Your Book Store App is : "+fetchCustData.get().getVerificationCode()+"");
                return true;
            }
            throw new UserNotFoundException(Util.USER_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND);
        }
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerRegisterDTO customerRegisterDTO, long id) {
        CustomerRegisterEntity customerRegisterEntity = new CustomerRegisterEntity();
        CustomerRegisterEntity fetchCustomer = customerRepo.findById(id);
        if(fetchCustomer!=null) {
            BeanUtils.copyProperties(fetchCustomer,customerRegisterEntity);
            customerRegisterEntity.setUpdateDate(LocalDate.now());
            customerRegisterEntity.setFirstName(customerRegisterDTO.getFirstName());
            customerRegisterEntity.setLastName(customerRegisterDTO.getLastName());
            customerRegisterEntity.setKyc(customerRegisterDTO.getKyc());
            customerRegisterEntity.setDob(customerRegisterDTO.getDob());
            customerRepo.save(customerRegisterEntity);
            jmsUtil.sendMail(fetchCustomer.getEmailId(),"Book Store App profile Updated","Dear "+customerRegisterEntity.getFirstName()+" , Your Profile is Updated Successfully!!!");
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(long id) {
        CustomerRegisterEntity customerRegisterEntity = customerRepo.findById(id);
        if(customerRegisterEntity!=null) {
            customerRepo.delete(customerRegisterEntity);
            return true;
        } else {
            return false;
        }
    }
}
