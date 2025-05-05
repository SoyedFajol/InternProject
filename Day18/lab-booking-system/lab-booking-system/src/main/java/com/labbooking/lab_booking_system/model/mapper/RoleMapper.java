package com.labbooking.lab_booking_system.model.mapper;

import com.labbooking.lab_booking_system.model.dto.RoleDto;
import com.labbooking.lab_booking_system.model.entity.Role;

public class RoleMapper {
    public static RoleDto toDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static Role toEntity(RoleDto dto) {
        return Role.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
