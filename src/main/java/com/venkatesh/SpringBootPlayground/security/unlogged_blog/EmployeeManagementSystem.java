package com.venkatesh.SpringBootPlayground.security.unlogged_blog;

import com.venkatesh.SpringBootPlayground.security.unlogged_blog.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystem.class, args);


        // https://www.unlogged.io/post/integrating-jwt-with-spring-security-6-in-spring-boot-3
    }
}
