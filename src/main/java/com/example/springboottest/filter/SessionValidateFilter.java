package com.example.springboottest.filter;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.springboottest.model.LoginSession;
import com.example.springboottest.repository.LoginSessionRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionValidateFilter extends OncePerRequestFilter {

    @Autowired
    LoginSessionRepository loginSessionRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException {
        String path = req.getRequestURI();
        return path.equals("/register") || path.equals("/login") || path.equals("/healthcheck");
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws
        ServletException, IOException {
        LoginSession loginSession = loginSessionRepository.findByID(req.getHeader("X-SESSION-ID"));
        OffsetDateTime now = OffsetDateTime.now();
        if (Objects.isNull(loginSession) || now.isAfter(loginSession.getExpireAt())) {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json; charset=utf-8");
            resp.setStatus(403);
        } else {
            chain.doFilter(req, resp);
        }
    }

}
