package com.ing.ingproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping("/v1/login")
    public String login() {
        return "Authentication Successful";
    }

    @GetMapping("/v1/logout")
    public String findProduct() {
        return "Logout Successful";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "This is for Admin only";
    }
}
