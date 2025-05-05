package com.labbooking.lab_booking_system.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDto {
    private String username; // This will usually be the email
    private String password;
}
