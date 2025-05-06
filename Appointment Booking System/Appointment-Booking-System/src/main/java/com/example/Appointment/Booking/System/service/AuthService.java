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

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    // Register user or doctor
    public String register(RegisterRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            return "Email already exists!";
        }

        Role role = roleRepository.findByName(requestDto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = userMapper.toEntity(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRoles(Collections.singleton(role));

        userRepository.save(user);
        return "Registration successful!";
    }

    // Login and generate JWT token
    public JwtResponseDto login(LoginRequestDto requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword())
        );

        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtTokenProvider.generateToken(user.getEmail());
        String role = user.getRoles().iterator().next().getName();

        String dashboard = "/login";
        if ("ROLE_USER".equals(role)) {
            dashboard = "/user/dashboard";
        } else if ("ROLE_DOCTOR".equals(role)) {
            dashboard = "/doctor/dashboard";
        }

        return new JwtResponseDto(token, role, dashboard);
    }
}
