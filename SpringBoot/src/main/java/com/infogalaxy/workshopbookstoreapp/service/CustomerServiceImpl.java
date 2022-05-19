package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.dto.CustomerRegisterDTO;
import com.infogalaxy.workshopbookstoreapp.dto.LoginDTO;
import com.infogalaxy.workshopbookstoreapp.entity.CustomerRegisterEntity;
import com.infogalaxy.workshopbookstoreapp.exception.InvalidCredentialException;
import com.infogalaxy.workshopbookstoreapp.exception.UserNotFoundException;
import com.infogalaxy.workshopbookstoreapp.repository.CustomerRepo;
import com.infogalaxy.workshopbookstoreapp.security.JWTTokenUtil;
import com.infogalaxy.workshopbookstoreapp.utility.JMSUtil;
import com.infogalaxy.workshopbookstoreapp.utility.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JMSUtil jmsUtil;

    @Autowired
    JWTTokenUtil jwtTokenUtil;
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

            Optional<CustomerRegisterEntity> fetchCustData = customerRepo.findCustomerRegisterEntityByUsername(customerRegisterEntity.getUsername());
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
            throw new UserNotFoundException("Customer with Given ID not Found.",HttpStatus.NOT_FOUND);
        }
    }

    /***
     *
     * @return
     */
    @Override
    public List<CustomerRegisterEntity> readAllCustomers() {
        List<CustomerRegisterEntity> customerRegisterEntityList = customerRepo.findAll();
        if(customerRegisterEntityList!=null) {
            return customerRegisterEntityList;
        }else {
            throw new UserNotFoundException("No Customer Data Found",HttpStatus.NOT_FOUND);
        }
    }

    /***
     *
     * @param token
     * @return
     */
    @Override
    public boolean isVerifiedUser(String token) {
        customerRepo.verifyCustomer(token);
        return true;
    }

    /***
     *
     * @param loginDTO
     * @return
     */
    @Override
    public UserLoginInfo login(LoginDTO loginDTO) {
        //Get Customer Data with given Login Credential
        Optional<CustomerRegisterEntity> fetchCustEntity = customerRepo.findCustomerRegisterEntityByUsername(loginDTO.getUsername());
        //Check if User available or not by Username
        if(fetchCustEntity.isPresent()) {
            //if username exist then check for Password match with passwordencoder
            if(passwordEncoder.matches(loginDTO.getPassword(),fetchCustEntity.get().getPassword())) {
                //if username and password match then check for User is Verified or not
                if(fetchCustEntity.get().getVerify()) {
                    //if all fine then create token and send to User
                    String createdToken = jwtTokenUtil.createToken(fetchCustEntity.get().getUsername(),fetchCustEntity.get().getRole());
                    return new UserLoginInfo(createdToken,fetchCustEntity.get().getFirstName(),fetchCustEntity.get().getRole());
                }
                //if User not verified then send email for verification status
                jmsUtil.sendMail(fetchCustEntity.get().getEmailId(),"Verification Status","Dear "+fetchCustEntity.get().getFirstName()+", Please Verify the Account First Then Login again.");

                return new UserLoginInfo("",fetchCustEntity.get().getFirstName());
            }
            //if username or password not match then response as Invalid credentials
            throw new InvalidCredentialException("Invalid Username OR Password",HttpStatus.UNPROCESSABLE_ENTITY);
        }
        //if user with given username not match then fire User not found exceptions
        throw new UserNotFoundException(Util.USER_NOT_FOUND_EXCEPTION_MESSAGE,HttpStatus.NOT_FOUND);
    }


}
