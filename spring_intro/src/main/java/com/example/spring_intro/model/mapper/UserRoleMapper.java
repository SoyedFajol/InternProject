package com.example.spring_intro.model.mapper;

import com.example.spring_intro.model.dto.UserRoleDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserRole;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserRoleMapper {

    public UserRole toEntity(UserRoleDTO dto, List<User> users) {
        UserRole entity = new UserRole();
        entity.setRoleName(dto.getRoleName());
        entity.setDescription(dto.getDescription());
        entity.setUsers(new HashSet<>(users));
        return entity;
    }

    public UserRoleDTO toDTO(UserRole entity) {
        UserRoleDTO dto = new UserRoleDTO();
        dto.setRoleName(entity.getRoleName());
        dto.setDescription(entity.getDescription());
        dto.setUserId(entity.getUsers().stream()
                .map(User::getId)
                .toList());
        return dto;
    }
}
