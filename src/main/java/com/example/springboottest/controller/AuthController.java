package com.example.springboottest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springboottest.entity.RegisterRequest;
import com.example.springboottest.service.AuthService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return ResponseEntity.ok(authService.register(req));
    }
    

    /*
    @PostMapping("/login")
    public String postMethodName(@RequestBody LoginRequest req) {
        User userModel = userRepository.findByUsername(req.username);

        if (userModel.getLoginUsername().equals(req.username) &&
        userModel.getPasswordHash().equals(req.password)) {
            System.out.println("Login succeeded");
            return "Login succeeded";
        } else {
            System.out.println("Login failed");
            return "Login failed";
        }
    }
    */

}
