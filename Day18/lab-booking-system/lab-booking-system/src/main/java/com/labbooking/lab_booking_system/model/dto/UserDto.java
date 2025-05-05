package com.labbooking.lab_booking_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private String mobile;
    private String gender;
    private String email;
    private String dob;
}
