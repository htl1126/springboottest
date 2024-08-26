package com.example.springboottest.service;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.Objects;
import java.util.Optional;

import com.example.springboottest.repository.LoginSessionRepository;
import com.example.springboottest.repository.UserInfoRepository;

import lombok.AllArgsConstructor;

import com.example.springboottest.model.LoginSession;
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
    UserInfoRepository userInfoRepository;
    @Autowired
    LoginSessionRepository loginSessionRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserInfo getUserObjectByEmail(String email) {
        UserInfo user = userInfoRepository.findByEmail(email);

        return user;
    }

    public AuthServiceResponse getUserInfo(String sessionID) {
        AuthServiceResponse resp = new AuthServiceResponse();
        resp.status = HttpStatus.OK;
        LoginSession loginSession = loginSessionRepository.findByID(sessionID);
        Optional<UserInfo> user = userInfoRepository.findById(loginSession.getUserID());
        resp.message = "account: " + user.get().getEmail();
        return resp;
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
            .positionID("positionID")
            .supervisorID("supervisorID")
            .id(UUID.randomUUID().toString())
            .build();
        userInfoRepository.save(newUser);

        resp.status = HttpStatus.OK;
        resp.message = "register successful";
        return resp;
    }

    public AuthServiceResponse login(LoginRequest req) {
        UserInfo user = getUserObjectByEmail(req.email);
        AuthServiceResponse resp = new AuthServiceResponse();

        if (Objects.isNull(user)) {
            resp.status = HttpStatus.NOT_FOUND;
            resp.message = "account does not exist";
            return resp;
        } else {
            // write to session table
            OffsetDateTime now = OffsetDateTime.now();
            String newSessionID = UUID.randomUUID().toString();
            LoginSession newSession = LoginSession.builder()
                .ID(newSessionID)
                .userID(user.getId())
                .createdAt(now)
                .expireAt(now.plusHours(1)) // should move this to config
                .build();
            loginSessionRepository.save(newSession);

            resp.status = HttpStatus.OK;
            resp.message = newSessionID;
            return resp;
        }
    }
}
