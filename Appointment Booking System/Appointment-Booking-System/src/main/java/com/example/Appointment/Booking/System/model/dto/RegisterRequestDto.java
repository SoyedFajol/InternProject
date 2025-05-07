package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RegisterRequestDto {
    private String fullName;
    private String mobileNumber;
    private String email;
    private String gender;
    private LocalDate dateOfBirth;
    private String password;
}
