package com.infogalaxy.workshopbookstoreapp.utility;

import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor
public class Util {

    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "Oops...User not found!";

    public static String generateOTP() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
    }

}
