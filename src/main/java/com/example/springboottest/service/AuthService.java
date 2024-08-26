package com.example.springboottest.service;

import java.util.UUID;
import java.util.Objects;

import com.example.springboottest.repository.UserRepository;

import lombok.AllArgsConstructor;

import com.example.springboottest.model.UserInfo;
import com.example.springboottest.entity.RegisterRequest;
import com.example.springboottest.entity.LoginRequest;
import com.example.springboottest.entity.AuthServiceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthService {

    @Autowired
    UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserInfo getUserObjectByEmail(String email) {
        UserInfo user = userRepository.findByEmail(email);

        return user;
    }
    
    public AuthServiceResponse register(RegisterRequest req) {
        // cases: 1. account already exists (ok)
        //        2. other exceptions (use Try and Catch)
        AuthServiceResponse resp = new AuthServiceResponse();

        UserInfo user = getUserObjectByEmail(req.email);
        if (!Objects.isNull(user)) {
            resp.status = HttpStatus.BAD_REQUEST;
            resp.message = "account already exists";
            return resp;
        }

        UserInfo newUser = UserInfo.builder()
            .email(req.email)
            .firstName(req.firstName)
            .lastName(req.lastName)
            .passwordHash(passwordEncoder.encode(req.password))
            .positionId("positionID")
            .supervisorId("supervisorID")
            .id(UUID.randomUUID().toString())
            .build();
        userRepository.save(newUser);
        resp.status = HttpStatus.OK;
        resp.message = "register successful";
        return resp;
    }

    // login
    // 1. validate account
    //    account exists;
    // 2. write to session table
    public AuthServiceResponse login(LoginRequest req) {
        UserInfo user = getUserObjectByEmail(req.email);
        AuthServiceResponse resp = new AuthServiceResponse();

        if (Objects.isNull(user)) {
            resp.status = HttpStatus.NOT_FOUND;
            resp.message = "account does not exist";
            return resp;
        } else {
            // write to session table
            resp.status = HttpStatus.OK;
            resp.message = "account exists";
            return resp;
        }
    }
}
