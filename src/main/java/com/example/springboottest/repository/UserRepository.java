package com.example.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottest.model.UserInfo;

/* DAO for PostgreSQL DB */
public interface UserRepository extends JpaRepository<UserInfo, String>{
    UserInfo findByEmail(String email);
}
