package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

@Data
public class RoleDto {
    private Long id;
    private String name; // "ROLE_USER", "ROLE_DOCTOR"
}
