package com.javawww.storeeverythingapp.service;

import com.javawww.storeeverythingapp.model.UserModel;
import com.javawww.storeeverythingapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService (UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserModel getUser(String name){
        log.info("Getting UserModel by name = {}", name);
        return userRepo.findByName(name);
    }

    public void addUser(String name){
        log.info("Adding user with name = {}", name);
        userRepo.save(new UserModel(name));
    }
}
