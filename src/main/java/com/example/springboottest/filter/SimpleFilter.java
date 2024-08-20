package com.example.springboottest.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SimpleFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException {
        String path = req.getCharacterEncoding();
        return !"UTF-8".equals(path); // process requests in UTF-8 encoding
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws
        ServletException, IOException {
        System.out.println("At SimpleFilter");
        System.out.println(req.getCharacterEncoding());
        chain.doFilter(req, resp);
    }

}
