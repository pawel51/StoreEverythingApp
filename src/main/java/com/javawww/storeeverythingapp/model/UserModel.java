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

    @NotNull(message = "{err.string.usernameReq}")
    @NotEmpty(message = "{err.string.usernameReq}")
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

    @Min(value = 1, message = "{err.string.age}") @Max(value = 99, message = "{err.string.age}")
    private int age;

    private Role role;

    @NotNull(message = "{err.string.emailReq}")
    @Email(message = "{err.string.email}",
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

//    public UserModel (String name, String surname, String username, String password, int age, Role role, String email) {
//        this.name = name;
//        this.surname = surname;
//        this.username = username;
//        this.password = password;
//        this.age = age;
//        this.role = role;
//        this.email = email;
//    }
}
