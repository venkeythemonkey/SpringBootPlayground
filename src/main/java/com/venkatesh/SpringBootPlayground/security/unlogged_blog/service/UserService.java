package com.venkatesh.SpringBootPlayground.security.unlogged_blog.service;

import com.venkatesh.SpringBootPlayground.security.unlogged_blog.model.User;
import com.venkatesh.SpringBootPlayground.security.unlogged_blog.model.UserPrincipal;
import com.venkatesh.SpringBootPlayground.security.unlogged_blog.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(username);
        return user.map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("UserName not found"));
    }

    public String addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "user added successfully";
    }

    public User findByUserName(String userName) {
        Optional<User> user = userRepo.findByUserName(userName);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
