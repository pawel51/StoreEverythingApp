package com.javawww.storeeverythingapp.service;

import com.javawww.storeeverythingapp.dto.UserDto;
import com.javawww.storeeverythingapp.enums.*;
import com.javawww.storeeverythingapp.model.UserModel;
import com.javawww.storeeverythingapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService extends GenericManagementService<UserModel, UserRepository>{

    public UserService (UserRepository userRepo) {
        super(userRepo);
    }

//    public UserModel getUser(Long id) {
//        log.info("Getting user with id = {}", id);
//        return userRepository.getById(id);
//    }

    public UserModel getUserByName(String name){
        log.info("Getting UserModel by name = {}", name);
        return repository.findByName(name);
    }

    public UserModel getUserByUsername(String userName) {
        log.info("Getting UserModel by userName = {}", userName);
        return repository.findByUsername(userName);
    }

//    public void addUser(String name, String surname, String login, String password, int age, Role role){
//        log.info("Adding user with name = {}", name);
//        userRepository.save(new UserModel(name, surname, login, password, age, role));
//    }

    public UserModel update(Long id, UserDto userDto) {
        UserModel userModel = getById(id);

        userModel.setId(userDto.getId());
        userModel.setName(userDto.getName());
        userModel.setSurname(userDto.getSurname());
        userModel.setUsername(userDto.getUsername());
        userModel.setPassword(userDto.getPassword());
        userModel.setAge(userDto.getAge());
        userModel.setRole(userDto.getRole());

        userModel = add(userModel);
        log.info("User with id = {} has been updated", id);
        return userModel;
    }

//    public void delete(Long id) {
//        delete(id);
//        log.info("User with id = {} has been removed", id);
//    }
}
