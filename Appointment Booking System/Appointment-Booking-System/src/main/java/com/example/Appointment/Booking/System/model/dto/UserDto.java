package com.example.Appointment.Booking.System.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String fullName;
    private String email;
    private String mobileNumber;
    private String gender;
    private LocalDate dateOfBirth;  // Changed to LocalDate
    private String password; // Password is secure, so handle it carefully
}
