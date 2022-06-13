package com.javawww.storeeverythingapp.dto;

import com.javawww.storeeverythingapp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    @Min(1) @Max(99)
    private int age;
    private Role role;
}
