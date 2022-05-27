package com.javawww.storeeverythingapp.controller;

import com.javawww.storeeverythingapp.model.Category;
import com.javawww.storeeverythingapp.model.UserModel;
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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String getAll(Model model) {
        List<UserModel> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "user/getAll";
    }

    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable Long id) {
        UserModel userModel = userService.getById(id);
        model.addAttribute("user", userModel);
        return "user/getUser";
    }
}
