package com.example.springboottest.controller;

import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboottest.entity.LoginRequest;
import com.example.springboottest.entity.Username;
import com.example.springboottest.model.UserLogin;
import com.example.springboottest.repository.UserLoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MyController {

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "This service is running healthy";
    }

    @PostMapping("/getName")
    public Map<String, String> getName(@RequestBody Username user) {
        Map<String, String> name = new HashMap<>();

        name.put("first", user.first);
        name.put("last", user.last);

        return name;
    }

    @Autowired
    UserLoginRepository userLoginRepository;

    @PostMapping("/login")
    public String postMethodName(@RequestBody LoginRequest req) {
        UserLogin userLoginModel = userLoginRepository.findByUsername(req.username);

        if (userLoginModel.getUsername().equals(req.username) &&
        userLoginModel.getPassword().equals(req.password)) {
            System.out.println("Login succeeded");
            return "Login succeeded";
        } else {
            System.out.println("Login failed");
            return "Login failed";
        }
    }
    

}
