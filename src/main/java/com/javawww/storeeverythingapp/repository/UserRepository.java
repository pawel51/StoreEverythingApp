package com.javawww.storeeverythingapp.repository;

import com.javawww.storeeverythingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByLogin(String login);
}
