package com.javawww.storeeverythingapp.controller;

import com.javawww.storeeverythingapp.model.UserModel;
import com.javawww.storeeverythingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;

    @ModelAttribute("user")
    public UserModel user() { return userService.getUserByName("Pawel"); }

    @GetMapping
    public String getHome(){
        return "homee";
    }

}
