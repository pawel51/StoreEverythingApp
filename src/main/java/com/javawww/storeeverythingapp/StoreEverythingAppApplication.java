package com.javawww.storeeverythingapp;

import com.javawww.storeeverythingapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StoreEverythingAppApplication {

    public static void main (String[] args) {
        SpringApplication.run(StoreEverythingAppApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.addUser("Pawel");
        };
    }

}
