package com.example.spring_intro.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // View for the login form
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        // Redirect users based on their role
        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/user/dashboard";
    }
}