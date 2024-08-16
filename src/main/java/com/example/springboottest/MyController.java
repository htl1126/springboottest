package com.example.springboottest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class MyController {

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "This service is running healthy";
    }

}
