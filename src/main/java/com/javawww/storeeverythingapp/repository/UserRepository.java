package com.javawww.storeeverythingapp.repository;

import com.javawww.storeeverythingapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByName(String name);
    UserModel findByUsername(String username);
}
