package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.config.Jwt.JwtTokenProvider;
import com.example.Appointment.Booking.System.model.dto.JwtResponseDto;
import com.example.Appointment.Booking.System.model.dto.LoginRequestDto;
import com.example.Appointment.Booking.System.model.dto.RegisterRequestDto;
import com.example.Appointment.Booking.System.model.entity.Role;
import com.example.Appointment.Booking.System.model.entity.User;
import com.example.Appointment.Booking.System.model.mapper.UserMapper;
import com.example.Appointment.Booking.System.repository.RoleRepository;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository; // Role repository to fetch roles
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    // Register a new user and assign the "USER" role by default
    public String register(RegisterRequestDto requestDto) {
        // Check if the email already exists in the database
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            return "Email already exists!";
        }

        // Convert the RegisterRequestDto to User entity
        User user = userMapper.toEntity(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));  // Encrypt the password

        // Fetch the "ROLE_USER" role from the database
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

        // Assign the "ROLE_USER" to the user
        user.getRoles().add(userRole);

        // Save the new user with the "USER" role
        userRepository.save(user);

        return "Registration successful!";
    }

    // Login and generate JWT token
    public JwtResponseDto login(LoginRequestDto requestDto) {
        // Authenticate the user using the provided email and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword())
        );

        // Find the user by email
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate a JWT token for the authenticated user
        String token = jwtTokenProvider.generateToken(user.getEmail());

        // Get the user's role (we assume only one role for simplicity)
        String role = user.getRoles().iterator().next().getName();
        String dashboard = "/login";  // Default dashboard URL

        // Based on the user's role, set the appropriate dashboard URL
        if ("ROLE_USER".equals(role)) {
            dashboard = "/user/dashboard";
        } else if ("ROLE_ADMIN".equals(role)) {
            dashboard = "/admin/dashboard";
        } else if ("ROLE_DOCTOR".equals(role)) {
            dashboard = "/doctor/dashboard";
        }

        // Return a JWT response with the token, role, and dashboard URL
        return new JwtResponseDto(token, role, dashboard);
    }
}
