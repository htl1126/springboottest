package com.example.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottest.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, String> {
    Roles findByID(String id);
}