package com.example.firstproject.model.mapper;

import com.example.firstproject.model.dto.UserRoleDTO;
import com.example.firstproject.model.entity.User;
import com.example.firstproject.model.entity.UserRole;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRoleMapper {

    public UserRole toEntity(UserRoleDTO dto) {
        UserRole userRole = new UserRole();
        userRole.setId(dto.getId());
        userRole.setRoleName(dto.getRoleName());
        userRole.setDescription(dto.getDescription());
        return userRole;
    }

    public UserRoleDTO toDTO(UserRole userRole) {
        UserRoleDTO dto = new UserRoleDTO();
        dto.setId(userRole.getId());
        dto.setRoleName(userRole.getRoleName());
        dto.setDescription(userRole.getDescription());

        // If the role has users, get the first user's ID for reference
        if (userRole.getUsers() != null && !userRole.getUsers().isEmpty()) {
            dto.setUserId(userRole.getUsers().iterator().next().getId());
        }

        return dto;
    }

    public UserRole updateEntityFromDTO(UserRole userRole, UserRoleDTO dto) {
        if (dto.getRoleName() != null) {
            userRole.setRoleName(dto.getRoleName());
        }
        if (dto.getDescription() != null) {
            userRole.setDescription(dto.getDescription());
        }
        return userRole;
    }
}