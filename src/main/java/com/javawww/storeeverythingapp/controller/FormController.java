package com.javawww.storeeverythingapp.controller;

import com.javawww.storeeverythingapp.dto.UserDto;
import com.javawww.storeeverythingapp.model.UserModel;
import com.javawww.storeeverythingapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class FormController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public FormController (UserService userService, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(){
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegisterForm(Model model){

        if (!model.containsAttribute("user")){
            model.addAttribute("user", new UserDto());
        }
        return "/registration";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegisterForm(Model model, @ModelAttribute("user") @Valid UserDto user,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            return "/registration";
        }

        // user already exists check
        UserModel exUser = userService.getUserByUsername(user.getUsername());
        if (exUser != null){
            model.addAttribute("userExistsMessage", "Username already exists");
            return "/registration";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserModel addedUser = userService.add(mapper.map(user, UserModel.class));
        if (addedUser != null){
            return "redirect:/login";
        }
        else
            return "/registration";
    }


}
