package com.example.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottest.model.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
    Users findByEmail(String email);
}
