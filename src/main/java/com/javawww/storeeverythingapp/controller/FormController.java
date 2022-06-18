package com.javawww.storeeverythingapp.controller;

import com.javawww.storeeverythingapp.dto.UserDto;
import com.javawww.storeeverythingapp.enums.Role;
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
    public String getLoginForm(Model model){
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegisterForm(Model model){

        if (!model.containsAttribute("user")){
            model.addAttribute("user", new UserDto());
        }
        return "/registration";
    }


    @PostMapping(value = "/registration")
    public String postRegisterForm(@ModelAttribute("user") @Valid UserDto user,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            return "/registration";
        }

        int errorCode = 0;
        // user already exists check
        UserModel exUser2 = userService.getUserByUsername(user.getUsername());
        if (exUser2 != null){
            errorCode += 2;
        }
        UserModel exUser4 = userService.getUserByEmail(user.getEmail());
        if (exUser4 != null){
            errorCode += 4;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.FULLUSER);
        UserModel userToAdd = mapper.map(user, UserModel.class);
        UserModel addedUser = userService.add(userToAdd);
        if (addedUser != null){
            return "redirect:/login";
        }
        else
            return "/registration";
    }


}
