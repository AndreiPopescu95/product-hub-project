package com.ing.ingproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class AuthenticationController {

    @GetMapping("/login")
    public String login() {
        return "Authentication Successful";
    }

    @GetMapping("/logout")
    public String findProduct() {
        return "Logout Successful";
    }
}
