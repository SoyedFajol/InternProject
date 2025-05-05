package com.labbooking.lab_booking_system.model.mapper;

import com.labbooking.lab_booking_system.model.dto.UserDTO;
import com.labbooking.lab_booking_system.model.entity.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .mobileNumber(user.getMobileNumber())
                .gender(user.getGender())
                .email(user.getEmail())
                .password(user.getPassword())
                .dateOfBirth(user.getDateOfBirth())
                .roles(user.getRoles()
                        .stream()
                        .map(RoleMapper::toDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static User toEntity(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .fullName(dto.getFullName())
                .mobileNumber(dto.getMobileNumber())
                .gender(dto.getGender())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .dateOfBirth(dto.getDateOfBirth())
                .roles(dto.getRoles()
                        .stream()
                        .map(RoleMapper::toEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
