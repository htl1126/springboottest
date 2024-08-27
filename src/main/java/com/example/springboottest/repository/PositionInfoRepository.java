package com.example.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottest.model.PositionInfo;


public interface PositionInfoRepository extends JpaRepository<PositionInfo, String>{
    PositionInfo findByID(String id);
}