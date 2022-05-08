package com.javawww.storeeverythingapp.service;

import com.javawww.storeeverythingapp.dto.UserDto;
import com.javawww.storeeverythingapp.enums.*;
import com.javawww.storeeverythingapp.model.User;
import com.javawww.storeeverythingapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService (UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    public User getUser(Long id) {
        log.info("Getting user with id = {}", id);
        return userRepository.getById(id);
    }
    public User getUserByName(String name){
        log.info("Getting UserModel by name = {}", name);
        return userRepository.findByName(name);
    }

    public User getUserByLogin(String login) {
        log.info("Getting UserModel by login = {}", login);
        return userRepository.findByLogin(login);
    }

    public void addUser(String name, String surname, String login, String password, Role role){
        log.info("Adding user with name = {}", name);
        userRepository.save(new User(name, surname, login, password, role));
    }

    public User update(Long id, UserDto userDto) {
        User user = getUser(id);

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setAge(userDto.getAge());
        user.setRole(userDto.getRole());

        user = userRepository.save(user);
        log.info("User with id = {} has been updated", id);
        return user;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("User with id = {} has been removed", id);
    }
}
