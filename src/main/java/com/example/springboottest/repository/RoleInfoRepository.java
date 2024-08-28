package com.example.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottest.model.RoleInfo;


public interface RoleInfoRepository extends JpaRepository<RoleInfo, String>{
    RoleInfo findByID(String id);
}