package com.example.firstproject.model.mapper;

import com.example.firstproject.model.dto.UserDTO;
import com.example.firstproject.model.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public User toEntity(UserDTO dto){
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        return user;
    }
    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setUserName(user.getUserName());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
