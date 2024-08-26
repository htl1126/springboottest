package com.example.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottest.model.UserInfo;


public interface UserInfoRepository extends JpaRepository<UserInfo, String>{
    UserInfo findByEmail(String email);
}
