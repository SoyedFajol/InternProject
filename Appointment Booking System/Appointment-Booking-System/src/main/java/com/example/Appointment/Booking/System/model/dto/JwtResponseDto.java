package com.example.Appointment.Booking.System.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDto {
    private String token;
    private String role;
    private String dashboardUrl;
}
