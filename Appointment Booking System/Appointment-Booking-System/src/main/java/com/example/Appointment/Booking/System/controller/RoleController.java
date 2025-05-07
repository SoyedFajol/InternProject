package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.entity.Role;
import com.example.Appointment.Booking.System.model.entity.User;
import com.example.Appointment.Booking.System.repository.RoleRepository;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // Assign a role to a user
    @PostMapping("/assign/{userId}")
    public ResponseEntity<String> assignRoleToUser(@PathVariable Long userId, @RequestParam String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(role);
        userRepository.save(user);

        return ResponseEntity.ok("Role assigned successfully");
    }
}
