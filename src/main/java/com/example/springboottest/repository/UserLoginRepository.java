package com.example.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottest.model.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer>{
    UserLogin findByUsername(String username);
}
