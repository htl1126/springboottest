package com.example.springboottest.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class WelLinkErrController implements ErrorController {
    
    @RequestMapping("/error")
    public String handlerError() {
        return "redirect:/";
    }
    
}
