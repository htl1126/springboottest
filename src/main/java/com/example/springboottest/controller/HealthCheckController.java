package com.example.springboottest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "controller HealthCheck Controller")
@RestController
public class HealthCheckController {

    @Operation(summary = "healthcheck", description = "check the system's health")
    @ApiResponse(responseCode = "200", description = "the system is running healthy")
    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "This service is running healthy";
    }

}
