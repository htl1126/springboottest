package com.example.springboottest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springboottest.entity.RegisterRequest;
import com.example.springboottest.entity.LoginRequest;
import com.example.springboottest.service.AuthService;
import com.example.springboottest.entity.AuthServiceResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;



@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "This service is running healthy";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        AuthServiceResponse resp = authService.register(req);
        return new ResponseEntity<>(resp.message, resp.status);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest req) {
        AuthServiceResponse resp = authService.login(req);
        return new ResponseEntity<>(resp.message, resp.status);
    }

    @GetMapping("/UserInfo")
    public ResponseEntity<String> getUserInfo(@RequestHeader("X-SESSION-ID") String sessionID) {
        AuthServiceResponse resp = authService.getUserInfo(sessionID);
        return new ResponseEntity<>(resp.message, resp.status);
    }
    
}
