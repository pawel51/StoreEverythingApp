package com.javawww.storeeverythingapp.dto;

import com.javawww.storeeverythingapp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private int age;
    private Role role;
}
