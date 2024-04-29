package com.venkatesh.SpringBootPlayground.security.unlogged_blog.repo;

import com.venkatesh.SpringBootPlayground.security.unlogged_blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    /**
     * findByName method is used to retrieve a user by their username.
     * It returns an Optional of UserInfo, which will be empty if no user is found.
     */
    Optional<User> findByUserName(String userName);
}