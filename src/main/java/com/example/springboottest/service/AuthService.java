package com.example.springboottest.service;

import java.util.UUID;

import com.example.springboottest.repository.UserRepository;

import lombok.AllArgsConstructor;

import com.example.springboottest.model.UserInfo;
import com.example.springboottest.entity.RegisterRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthService {

    @Autowired
    UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public String register(RegisterRequest req) {
        var user = UserInfo.builder()
            .email(req.email)
            .firstName(req.firstName)
            .lastName(req.lastName)
            .passwordHash(passwordEncoder.encode(req.password))
            .positionId("positionID")
            .supervisorId("supervisorID")
            .id(UUID.randomUUID().toString())
            .build();
        userRepository.save(user);
        return "register successful";
    }
}
