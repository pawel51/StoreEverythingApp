package com.javawww.storeeverythingapp.model;

import com.javawww.storeeverythingapp.enums.*;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    @NotNull
    private String username;

    /*
        ^                                   # start of line
          (?=.*[0-9])                       # positive lookahead, digit [0-9]
          (?=.*[a-z])                       # positive lookahead, one lowercase character [a-z]
          (?=.*[A-Z])                       # positive lookahead, one uppercase character [A-Z]
          (?=.*[!@#&()–[{}]:;',?/*~$^+=<>]) # positive lookahead, one of the special character in this [..]
          .                                 # matches anything
          {8,20}                            # length at least 8 characters and maximum of 20 characters
        $                                   # end of line
     */

    @NotNull
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$%^+.=<|>]).{8,20}$" , message = "{err.string.password}")
    private String password;

    @Min(1) @Max(99)
    private int age;

    @NotNull
    private Role role;

    public UserModel (String name, String surname, String username, String password, int age, Role role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;
    }
}
