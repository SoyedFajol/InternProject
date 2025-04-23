package com.example.firstproject.controller;

import com.example.firstproject.model.dto.UserRoleDTO;
import com.example.firstproject.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRole(@RequestBody UserRoleDTO roleDTO) {
        userRoleService.save(roleDTO);
        return ResponseEntity.ok("Role created successfully");
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDTO>> getAllRoles() {
        return ResponseEntity.ok(userRoleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDTO> getRoleById(@PathVariable Long id) {
        return userRoleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRole(@PathVariable Long id, @RequestBody UserRoleDTO roleDTO) {
        if (userRoleService.update(id, roleDTO)) {
            return ResponseEntity.ok("Role updated successfully");
        } else {
            return ResponseEntity.status(404).body("Role not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        if (userRoleService.delete(id)) {
            return ResponseEntity.ok("Role deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Role not found");
        }
    }

    @PostMapping("/setRole/{userId}/{roleId}")
    public ResponseEntity<String> setUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        if (userRoleService.setUserRole(userId, roleId)) {
            return ResponseEntity.ok("User role set successfully");
        } else {
            return ResponseEntity.badRequest().body("User or role not found");
        }
    }
}