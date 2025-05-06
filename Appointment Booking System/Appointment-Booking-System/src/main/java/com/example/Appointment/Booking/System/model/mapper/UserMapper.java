package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.RegisterRequestDto;
import com.example.Appointment.Booking.System.model.dto.UserDto;
import com.example.Appointment.Booking.System.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setGender(user.getGender());
        return dto;
    }

    public User toEntity(RegisterRequestDto dto) {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setMobileNumber(dto.getMobileNumber());
        user.setGender(dto.getGender());
        user.setDateOfBirth(dto.getDateOfBirth());
        return user;
    }
}
