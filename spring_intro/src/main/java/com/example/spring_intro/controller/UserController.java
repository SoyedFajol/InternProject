package com.example.spring_intro.controller;

import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.service.UserRoleService;
import com.example.spring_intro.service.UserRoleService;
import com.example.spring_intro.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final UserRoleService roleService;

    public UserController(UserService userService, UserRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    private boolean hasRequiredRole(Long userId) {
        return roleService.accessAuthority(userId, "ADMIN") ||
                roleService.accessAuthority(userId, "USER") ||
                roleService.accessAuthority(userId, "MODERATOR") ||
                roleService.accessAuthority(userId, "AUTHOR");
    }

    @PostMapping("/create")
    public ResponseEntity<String> save(@RequestBody UserDTO dto) {
     userService.save(dto);
     return ResponseEntity.ok("User saved successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UserDTO dto) {
        if (hasRequiredRole(id)) {
            String response = userService.update(id, dto);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>("You don't have permission to update user", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        if (hasRequiredRole(id)) {
            String response = userService.deleteById(id);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>("You don't have permission to delete user", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = userService.getAll();
        return ResponseEntity.ok(users);
    }
}
