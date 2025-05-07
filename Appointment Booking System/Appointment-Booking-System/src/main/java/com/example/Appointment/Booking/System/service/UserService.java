package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.User;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Get user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update user information
    public User updateUser(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setMobileNumber(updatedUser.getMobileNumber());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setGender(updatedUser.getGender());
        existingUser.setDateOfBirth(updatedUser.getDateOfBirth());

        // Encrypt password before saving
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

        return userRepository.save(existingUser);
    }

    // Delete user
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(existingUser);
    }
}
