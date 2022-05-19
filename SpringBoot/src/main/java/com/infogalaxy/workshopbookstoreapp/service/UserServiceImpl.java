package com.infogalaxy.workshopbookstoreapp.service;

import com.infogalaxy.workshopbookstoreapp.dto.UserDTO;
import com.infogalaxy.workshopbookstoreapp.dto.LoginDTO;
import com.infogalaxy.workshopbookstoreapp.entity.UserEntity;
import com.infogalaxy.workshopbookstoreapp.exception.InvalidCredentialException;
import com.infogalaxy.workshopbookstoreapp.exception.UserNotFoundException;
import com.infogalaxy.workshopbookstoreapp.repository.UserRepo;
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
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepo customerRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JMSUtil jmsUtil;

    @Autowired
    JWTTokenUtil jwtTokenUtil;
    /***
     * Service Implementation for Add Customer to App using BookStoreApp Repository
     * @param userDTO
     * @return
     */
    @Override
    public boolean addCustomer(UserDTO userDTO) {
        if(!customerRepo.existsByEmailId(userDTO.getEmailId())) {
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userDTO,userEntity);
            userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userEntity.setVerificationCode(Util.generateOTP());
            userEntity.setRegisterDate(LocalDate.now());
            userEntity.setVerify(false);
            customerRepo.save(userEntity);

            Optional<UserEntity> fetchCustData = customerRepo.findUserEntityByUsername(userEntity.getUsername());
            if(fetchCustData.isPresent()) {
                jmsUtil.sendMail(userDTO.getEmailId(),"Verification Code for Book Store App","Hello "+userDTO.getFirstName()+", Verification Code for Your Book Store App is : "+fetchCustData.get().getVerificationCode()+"");
                return true;
            }
            throw new UserNotFoundException(Util.USER_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND);
        }
        return false;
    }

    @Override
    public boolean updateCustomer(UserDTO userDTO, long id) {
        UserEntity userEntity = new UserEntity();
        UserEntity fetchCustomer = customerRepo.findById(id);
        if(fetchCustomer!=null) {
            BeanUtils.copyProperties(fetchCustomer,userEntity);
            userEntity.setUpdateDate(LocalDate.now());
            userEntity.setFirstName(userDTO.getFirstName());
            userEntity.setLastName(userDTO.getLastName());
            userEntity.setKyc(userDTO.getKyc());
            userEntity.setDob(userDTO.getDob());
            customerRepo.save(userEntity);
            jmsUtil.sendMail(fetchCustomer.getEmailId(),"Book Store App profile Updated","Dear "+userEntity.getFirstName()+" , Your Profile is Updated Successfully!!!");
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(long id) {
        UserEntity userEntity = customerRepo.findById(id);
        if(userEntity!=null) {
            customerRepo.delete(userEntity);
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
    public List<UserEntity> readAllCustomers() {
        List<UserEntity> userEntityList = customerRepo.findAll();
        if(userEntityList!=null) {
            return userEntityList;
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
        customerRepo.verifyUser(token);
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
        Optional<UserEntity> fetchCustEntity = customerRepo.findUserEntityByUsername(loginDTO.getUsername());
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
