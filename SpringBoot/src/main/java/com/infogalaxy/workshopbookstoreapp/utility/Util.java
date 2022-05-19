package com.infogalaxy.workshopbookstoreapp.utility;

import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor
public class Util {

    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "Sorry... User Not Found!";
    public static final String ROLE_ADMIN = "admin";
    private static final String ROLE_USER = "user";

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
}
