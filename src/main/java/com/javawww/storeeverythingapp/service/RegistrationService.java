package com.javawww.storeeverythingapp.service;

import com.javawww.storeeverythingapp.enums.Role;
import com.javawww.storeeverythingapp.model.RegistrationRequest;
import com.javawww.storeeverythingapp.model.UserModel;
import com.javawww.storeeverythingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserRepository userRepository;

    public UserModel register(RegistrationRequest request) {
        var user = new UserModel();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setRole(Role.LIMITEDUSER);
        user.setSurname(request.getSurname());
        user.setPassword((request.getPassword()));
        user.setUsername((request.getUsername()));
        userRepository.save(user);

        return user;
    }
}
