package com.example.springboottest.controller;

import java.util.Map;
import java.util.Collection;

import org.springframework.web.bind.annotation.RestController;

import com.example.springboottest.entity.RegisterRequest;
import com.example.springboottest.entity.LoginRequest;
import com.example.springboottest.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.springboottest.entity.AuthServiceResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;


@Tag(name = "controller Authentication Controller")
@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/")
    public String index() {
        return "login successfully";
    }

    @GetMapping("/myattr")
    public Map<String, Object> myAttr(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }

    @GetMapping("/myauth")
    public Collection<? extends GrantedAuthority> myAuth(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAuthorities();
    }

    @Operation(summary = "register", description = "register or create a new account")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "account was created successfully"),
        @ApiResponse(responseCode = "400", description = "incorrect request")
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        AuthServiceResponse resp = authService.register(req);
        return new ResponseEntity<>(resp.message, resp.status);
    }

    @Operation(summary = "login", description = "log into the system")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "login successful"),
        @ApiResponse(responseCode = "400", description = "incorrect request")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest req) {
        AuthServiceResponse resp = authService.login(req);
        return new ResponseEntity<>(resp.message, resp.status);
    }

    @Operation(summary = "get user", description = "get user information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "get user info succesfully"),
        @ApiResponse(responseCode = "400", description = "incorrect request")
    })
    @GetMapping("/user")
    public ResponseEntity<String> getUser(@RequestHeader("X-SESSION-ID") String sessionID) {
        AuthServiceResponse resp = authService.getUser(sessionID);
        return new ResponseEntity<>(resp.message, resp.status);
    }

}
