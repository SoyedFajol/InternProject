package com.example.firstproject.service;

import com.example.firstproject.model.dto.UserRoleDTO;
import com.example.firstproject.model.entity.User;
import com.example.firstproject.model.entity.UserRole;
import com.example.firstproject.model.mapper.UserRoleMapper;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;
    private final UserRepository userRepository;

    public UserRoleService(UserRoleRepository userRoleRepository, UserRoleMapper userRoleMapper, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleMapper = userRoleMapper;
        this.userRepository = userRepository;
    }

    public void save(UserRoleDTO dto) {
        UserRole userRole = userRoleMapper.toEntity(dto);
        userRoleRepository.save(userRole);
    }

    public List<UserRoleDTO> getAll() {
        List<UserRole> roles = userRoleRepository.findAll();
        return roles.stream().map(userRoleMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<UserRoleDTO> findById(Long id) {
        return userRoleRepository.findById(id).map(userRoleMapper::toDTO);
    }

    public boolean update(Long id, UserRoleDTO dto) {
        Optional<UserRole> roleOpt = userRoleRepository.findById(id);
        if (roleOpt.isPresent()) {
            UserRole role = roleOpt.get();
            userRoleMapper.updateEntityFromDTO(role, dto);
            userRoleRepository.save(role);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        Optional<UserRole> role = userRoleRepository.findById(id);
        if (role.isPresent()) {
            userRoleRepository.delete(role.get());
            return true;
        }
        return false;
    }

    public boolean setUserRole(Long userId, Long roleId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<UserRole> roleOpt = userRoleRepository.findById(roleId);

        if (userOpt.isPresent() && roleOpt.isPresent()) {
            User user = userOpt.get();
            UserRole role = roleOpt.get();

            if (role.getUsers() == null) {
                role.setUsers(new HashSet<>());
            }
            role.getUsers().add(user);
            userRoleRepository.save(role);
            return true;
        }
        return false;
    }

    public boolean isCreateBlogAccessAuthority(Long userId) {
        if (userId == null) {
            return false;
        }

        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return false;
        }

        Optional<UserRole> roleOpt = userRoleRepository.findByUserId(userId);
        if (roleOpt.isEmpty()) {
            return false;
        }

        UserRole role = roleOpt.get();
        return "ADMIN".equalsIgnoreCase(role.getRoleName()) ||
                "AUTHOR".equalsIgnoreCase(role.getRoleName()) || "USER".equalsIgnoreCase(role.getRoleName()) || "MODERATOR".equalsIgnoreCase(role.getRoleName());
    }
}