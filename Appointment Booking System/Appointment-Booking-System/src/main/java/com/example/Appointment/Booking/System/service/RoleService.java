package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.Role;
import com.example.Appointment.Booking.System.model.entity.User;
import com.example.Appointment.Booking.System.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    // Assign a role to a user
    public String assignRoleToUser(User user, String roleName) {
        // Fetch the role by name
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Add role to user and save user
        user.getRoles().add(role);
        // Assuming the user is saved in UserService
        return "Role assigned successfully";
    }

    // Get a role by name (optional)
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}