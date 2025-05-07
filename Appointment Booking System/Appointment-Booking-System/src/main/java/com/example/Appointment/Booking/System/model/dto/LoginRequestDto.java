package com.example.Appointment.Booking.System.model.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,}$", message = "Password must contain at least one uppercase letter, one digit, and one special character.")
    private String password;
}
