package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.*;
import com.example.Appointment.Booking.System.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto request) {
        String result = authService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginRequestDto request) {
        JwtResponseDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
