package com.infogalaxy.workshopbookstoreapp.utility;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor
public class Util {

    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "Sorry... User Not Found!";
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_USER = "user";
    public static final String VERIFICATION_LINK = "http://localhost:8080/bookstoreapp/api/user/verification";

    /***
     * This function generate teh 6 Digit random OTP number
     * @return
     */
    public static String generateOTP() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
    }

    /***
     * This Function genertae the Random ID
     * @return
     */
    public static String randomIdGenerator() {
        return UUID.randomUUID().toString();
    }

    public static String currentDateTime() {
        return LocalDateTime.now ().format (DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss"));
    }

}
