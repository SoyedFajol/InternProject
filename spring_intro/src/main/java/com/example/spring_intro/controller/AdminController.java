package com.example.spring_intro.controller;

import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/edit-user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/edit-user";
        }
        userService.update(id, user);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/dashboard";
    }
}