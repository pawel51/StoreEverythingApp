package com.javawww.storeeverythingapp.controller;

import com.javawww.storeeverythingapp.model.RegistrationRequest;
import com.javawww.storeeverythingapp.model.UserModel;
import com.javawww.storeeverythingapp.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public UserModel register(@Valid @RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
