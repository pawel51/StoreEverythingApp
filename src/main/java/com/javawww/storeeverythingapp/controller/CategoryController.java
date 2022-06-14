package com.javawww.storeeverythingapp.controller;

import com.javawww.storeeverythingapp.model.Category;
import com.javawww.storeeverythingapp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public String getAll(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "category/getAll";
    }

    @GetMapping("/add")
    public String createNote(Model model) {
        if (!model.containsAttribute("category")) {
            model.addAttribute("category", new Category());
        }
        return "category/addCategory";
    }

    @PostMapping("/add")
    public String addCategory(@Valid Category category,
                              RedirectAttributes redirectAttributes) {
        categoryService.add(category);
        redirectAttributes.addFlashAttribute("success", "Record has been successfully added.");
        return "redirect:/category/";
    }
}
