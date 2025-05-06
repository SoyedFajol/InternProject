package com.example.Appointment.Booking.System.model.mapper;


import com.example.Appointment.Booking.System.model.dto.RoleDto;
import com.example.Appointment.Booking.System.model.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public static RoleDto toDto(Role role) {
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }

    public static Role toEntity(RoleDto dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }
}
