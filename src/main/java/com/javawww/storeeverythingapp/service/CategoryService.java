package com.javawww.storeeverythingapp.service;

import com.javawww.storeeverythingapp.model.Category;
import com.javawww.storeeverythingapp.model.Note;
import com.javawww.storeeverythingapp.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryService extends GenericManagementService<Category, CategoryRepository>{

    public CategoryService (CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

    public Optional<Category> findByName(String name){
        return repository.findByName(name);
    }
}
