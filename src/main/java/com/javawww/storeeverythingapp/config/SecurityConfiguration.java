package com.javawww.storeeverythingapp.config;

import com.javawww.storeeverythingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth

                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/users").hasAuthority("ADMIN")
                .mvcMatchers("/category").hasAuthority("ADMIN")
                .mvcMatchers("/note/add").hasAnyAuthority("ADMIN", "FULLUSER")
                .antMatchers("/resources/**",
                        "/css/**", "/images/**", "/js/**",
                        "/webjars/**",
                        "/assets/**",
                        "/registration**").permitAll()
                .anyRequest().authenticated()
                .and()
                .rememberMe().userDetailsService(userService).key("user-name")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .deleteCookies("user-name", "JSESSIONID")
                .logoutSuccessUrl("/index")
                .and()
                .sessionManagement()
                .invalidSessionUrl("/index");
    }


}
