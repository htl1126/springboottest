package com.example.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottest.model.LoginSession;


public interface LoginSessionRepository extends JpaRepository<LoginSession, String>{
    LoginSession findByID(String id);
}
