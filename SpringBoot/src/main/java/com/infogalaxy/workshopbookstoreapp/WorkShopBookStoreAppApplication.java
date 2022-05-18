package com.infogalaxy.workshopbookstoreapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@Slf4j
public class WorkShopBookStoreAppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WorkShopBookStoreAppApplication.class, args);
        log.info("Active Profile : {} ",context.getEnvironment().getActiveProfiles());
    }

    /***
     * BCryptPasswordEncoder Bean created for Autowire in service
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
