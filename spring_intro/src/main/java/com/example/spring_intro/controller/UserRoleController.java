package com.example.spring_intro.controller;

import com.example.spring_intro.model.dto.UserRoleDTO;
import com.example.spring_intro.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> save(@RequestBody UserRoleDTO dto) {
        String response = userRoleService.save(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/assign")
    public ResponseEntity<String> setRole(@RequestBody UserRoleDTO roleDto) {
        String response = userRoleService.setRole(roleDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDTO> getById(@PathVariable Long id) {
        UserRoleDTO userRole = userRoleService.findById(id);
        if (userRole == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userRole);
    }

    @GetMapping("/role/{roleName}")
    public ResponseEntity<UserRoleDTO> getByRoleName(@PathVariable String roleName) {
        UserRoleDTO userRole = userRoleService.findByRoleName(roleName);
        if (userRole == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userRole);
    }

    @PatchMapping("/role/{roleName}")
    public ResponseEntity<String> updateRole(@PathVariable String roleName,
                                             @RequestBody UserRoleDTO dto) {
        String response = userRoleService.updateRole(roleName, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        String response = userRoleService.deleteRole(id);
        if (response.contains("deleted")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body(response);
    }
}
