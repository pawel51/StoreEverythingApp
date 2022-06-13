package com.javawww.storeeverythingapp.service;

import com.javawww.storeeverythingapp.dto.UserDto;
import com.javawww.storeeverythingapp.enums.*;
import com.javawww.storeeverythingapp.model.UserModel;
import com.javawww.storeeverythingapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class UserService extends GenericManagementService<UserModel, UserRepository> implements UserDetailsService {

    public UserService (UserRepository userRepo) {
        super(userRepo);
    }


    public UserModel getUserByName(String name){
        log.info("Getting UserModel by name = {}", name);
        return repository.findByName(name);
    }

    public UserModel getUserByUsername(String userName) {
        log.info("Getting UserModel by userName = {}", userName);
        return repository.findByUsername(userName);
    }


    public UserModel update(Long id, UserDto userDto) {
        UserModel userModel = getById(id);

        userModel.setId(userDto.getId());
        userModel.setName(userDto.getName());
        userModel.setSurname(userDto.getSurname());
        userModel.setUsername(userDto.getUsername());
        userModel.setPassword(userDto.getPassword());
        userModel.setAge(userDto.getAge());
        userModel.setRole(userDto.getRole());

        userModel = add(userModel);
        log.info("User with id = {} has been updated", id);
        return userModel;
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        UserModel user = repository.findByUsername(username);
        User.UserBuilder builder = null;
        if (user != null){
            builder = User.withUsername(username);
            builder.disabled(false)
                    .password(user.getPassword());

            builder.authorities(getAuthorities(user));
            return builder.build();
        }
        else {
            throw new UsernameNotFoundException(username + "not found");
        }
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(UserModel user) {
        String[] userRoles = new String[1];
        userRoles[0] = user.getRole().toString();
        return AuthorityUtils.createAuthorityList(userRoles);
    }

}
