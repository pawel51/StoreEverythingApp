package com.javawww.storeeverythingapp.controller;

import com.javawww.storeeverythingapp.model.User;
import com.javawww.storeeverythingapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public String getUser(Model model, @PathVariable Long id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user/getUser";
    }
}
