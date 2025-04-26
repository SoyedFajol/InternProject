package com.example.spring_intro.service;

import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.mapper.UserMapper;
import com.example.spring_intro.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }


    public void save(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        repository.save(user);
    }


    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null);
    }

    public String deleteById(Long id) {
        if (!repository.existsById(id)) {
            return "User not found with id: " + id;
        }
        repository.deleteById(id);
        return "User deleted successfully";
    }


    public String update(Long id, UserDTO dto) {
        try {
            User existingUser = repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
            User updatedUser = userMapper.toEntity(dto);
            updatedUser.setId(existingUser.getId());
            repository.save(updatedUser);
            return "User updated successfully with id: " + id;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public List<UserDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
