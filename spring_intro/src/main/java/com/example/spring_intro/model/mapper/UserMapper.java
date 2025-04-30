package com.example.spring_intro.model.mapper;

import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDTO dto) {
        User user = new User();
       user.setUsername(dto.getUsername());
       user.setPassword(dto.getPassword());
       user.setEmail(dto.getEmail());
       user.setPhone(dto.getPhone());


       return user;
    }
    public UserDTO toDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        return dto;
    }
}
