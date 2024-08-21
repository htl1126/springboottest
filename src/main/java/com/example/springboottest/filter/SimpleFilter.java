package com.example.springboottest.filter;

import java.io.IOException;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SimpleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws
        ServletException, IOException {
        String auth = Objects.toString(req.getHeader("Authorization"));
        if (auth.contains("Bearer")) {
            System.out.println("At SimpleFilter");
            chain.doFilter(req, resp);
        } else {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json; charset=utf-8");
            resp.setStatus(401);
        }
    }

}
