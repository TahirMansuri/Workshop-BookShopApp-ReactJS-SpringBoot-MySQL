package com.infogalaxy.workshopbookstoreapp.responses;

import com.infogalaxy.workshopbookstoreapp.service.UserLoginInfo;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */
public class LoginResponse extends Response{
    private final UserLoginInfo userLoginInfo;

    public LoginResponse(String message, int statusCode, UserLoginInfo userLoginInfo) {
        super(message,statusCode);
        this.userLoginInfo = userLoginInfo;
    }

    public UserLoginInfo getLoginInfo() {
        return userLoginInfo;
    }
}
