package com.infogalaxy.workshopbookstoreapp.controller;

import com.infogalaxy.workshopbookstoreapp.dto.CustomerRegisterDTO;
import com.infogalaxy.workshopbookstoreapp.dto.LoginDTO;
import com.infogalaxy.workshopbookstoreapp.entity.CustomerRegisterEntity;
import com.infogalaxy.workshopbookstoreapp.responses.LoginResponse;
import com.infogalaxy.workshopbookstoreapp.responses.Response;
import com.infogalaxy.workshopbookstoreapp.service.CustomerServiceImpl;
import com.infogalaxy.workshopbookstoreapp.service.UserLoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookstoreapp/api")
public class BookStoreAppController {

    @Autowired
    CustomerServiceImpl customerService;

    /***
     * Handling REST API call for home
     * @return
     */
    @RequestMapping(value = {"","/","/home"})
    public  String home() {
        return "<h1>Welcome to Book Store App</h1>";
    }

    /***
     * Add customer data to system
     * @param customerRegisterDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Response> addCustomer(@Valid @RequestBody CustomerRegisterDTO customerRegisterDTO) {
        boolean isRegistered = customerService.addCustomer(customerRegisterDTO);
        if(!isRegistered) {
            new ResponseEntity<Response>(new Response("Customer Already Registered. Please Login.",208),HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<Response>(new Response("Registered Successfully!!!",201), HttpStatus.CREATED);
    }

    /***
     * API to Update Customer from system
     * @param customerRegisterDTO
     * @param id
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateCustomer(@RequestBody CustomerRegisterDTO customerRegisterDTO,@PathVariable long id) {
        boolean isUpdated = customerService.updateCustomer(customerRegisterDTO,id);
        if(!isUpdated) {
            return new ResponseEntity<Response>(new Response("Customer Not Found!!!",HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Response>(new Response("Updated Successfully!!!",201),HttpStatus.OK);
        }
    }

    /***
     * API to Delete Customer from System
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<Response>(new Response("Customer Not Found...!!!",HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    /***
     * API for Accessing Cutomer Data
     * @return
     */
    @GetMapping("/read")
    public ResponseEntity<List<CustomerRegisterEntity>> getAllCustomers() {
        return new ResponseEntity<List<CustomerRegisterEntity>>(customerService.readAllCustomers(),HttpStatus.OK);
    }

    /***
     * Login API for User Login check
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        UserLoginInfo userLoginInfo = customerService.login(loginDTO);
        if(!userLoginInfo.getToken().isEmpty()) {
            return new ResponseEntity<LoginResponse>(new LoginResponse("Login Successfull!!!!", 201,userLoginInfo),HttpStatus.OK);
        }
        return new ResponseEntity<>(new LoginResponse("Check Your Mail for Verification", 203, userLoginInfo),HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
}
