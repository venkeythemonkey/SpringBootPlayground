package com.venkatesh.SpringBootPlayground.security.unlogged_blog.controller;

import com.venkatesh.SpringBootPlayground.security.unlogged_blog.model.Role;
import com.venkatesh.SpringBootPlayground.security.unlogged_blog.model.User;
import com.venkatesh.SpringBootPlayground.security.unlogged_blog.repo.UserRepo;
import com.venkatesh.SpringBootPlayground.security.unlogged_blog.service.JwtService;
import com.venkatesh.SpringBootPlayground.security.unlogged_blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        user.setRole(user.getRole());
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            User loggedInUser = userService.findByUserName(user.getUserName());
            return jwtService.generateToken(user.getUserName(), loggedInUser.getRole());
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }
}
