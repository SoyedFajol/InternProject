package com.example.spring_intro.controller;

import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping; // Missing import added

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "user/dashboard";
    }
}