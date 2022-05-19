package com.infogalaxy.workshopbookstoreapp.service;

/**
 * UserLogin Info for Managing the Token and Role after Login
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */

import lombok.Data;

@Data
public class UserLoginInfo {
    private String token;
    private String firstname;
    private String role;

    public UserLoginInfo(String token, String firstname) {
        this.token = token;
        this.firstname = firstname;
    }

    public UserLoginInfo(String token,String firstname, String role) {
        this(token,firstname);
        this.role = role;
    }
}
