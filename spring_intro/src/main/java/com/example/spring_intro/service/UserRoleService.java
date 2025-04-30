package com.example.spring_intro.service;

import com.example.spring_intro.exception.IllegalException;
import com.example.spring_intro.model.dto.UserRoleDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserRole;
import com.example.spring_intro.model.mapper.UserRoleMapper;
import com.example.spring_intro.repository.UserRepository;
import com.example.spring_intro.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserRoleService {
     private final UserRoleRepository userRoleRepository;
     private final UserRoleMapper userRoleMapper;
     private final UserRepository userRepository;

     public String save(UserRoleDTO dto) {
          try {
               List<User> users = userRepository.findAllById(dto.getUserId());
               if (users.isEmpty()) {
                    throw new IllegalArgumentException("Users not found with ids: " + dto.getUserId());
               }
               UserRole userRole = userRoleMapper.toEntity(dto, users);
               userRoleRepository.save(userRole);
               return "User role saved successfully";
          } catch (IllegalArgumentException e) {
               return "Failed to save user role: " + e.getMessage();
          }
     }

     public String setRole(UserRoleDTO roleDto) {
          try {
               List<User> users = userRepository.findAllById(roleDto.getUserId());
               if (users.isEmpty()) {
                    return "Users not found with ids: " + roleDto.getUserId();
               }
               Optional<UserRole> optionalUserRole = userRoleRepository.findByRoleName(roleDto.getRoleName());
               if (optionalUserRole.isEmpty()) {
                    return "Role not found with name: " + roleDto.getRoleName();
               }
               UserRole userRole = optionalUserRole.get();
               Set<User> updatedUserSet = userRole.getUsers();
               if (updatedUserSet == null) {
                    updatedUserSet = new HashSet<>();
               }
               updatedUserSet.addAll(users);
               userRole.setUsers(updatedUserSet);
               userRoleRepository.save(userRole);
               return "Role set successfully. Role Id: " + userRole.getId() + " User Ids: " + roleDto.getUserId();
          } catch (Exception e) {
               return "Failed to set role. Exception: " + e.getMessage();
          }
     }

     public UserRoleDTO findById(Long id) {
          UserRole userRole = userRoleRepository.findById(id).orElse(null);
          if (userRole == null) {
               return null;
          }
          return userRoleMapper.toDTO(userRole);
     }

     public UserRoleDTO findByRoleName(String roleName) {
          Optional<UserRole> optionalUserRole = userRoleRepository.findByRoleName(roleName);
          if (optionalUserRole.isEmpty()) {
               return null;
          }
          return userRoleMapper.toDTO(optionalUserRole.get());
     }

     public String updateRole(String roleName, UserRoleDTO dto) {
          try {
               Optional<UserRole> optionalUserRole = userRoleRepository.findByRoleName(roleName);
               if (optionalUserRole.isEmpty()) {
                    return "Role not found with name: " + roleName;
               }
               UserRole userRole = optionalUserRole.get();
               userRole.setRoleName(dto.getRoleName());
               userRole.setDescription(dto.getDescription());

               userRoleRepository.save(userRole);
               return "Role updated successfully. Role Id: " + userRole.getId();
          } catch (Exception e) {
               return "Failed to update role. Exception: " + e.getMessage();
          }
     }

     public String deleteRole(Long id) {
          try {
               Optional<UserRole> userRole = userRoleRepository.findById(id);
               if (userRole.isPresent()) {
                    userRoleRepository.delete(userRole.get());
                    return "Role deleted successfully. Role Id: " + id;
               } else {
                    return "Role not found with id: " + id;
               }
          } catch (Exception e) {
               return "Failed to delete role. Exception: " + e.getMessage();
          }
     }

     public boolean accessAuthority(Long userId, String roleName) {
          return userRepository.findById(userId)
                  .map(user -> user.getRoles().stream()
                          .anyMatch(role -> role.getRoleName() != null &&
                                  role.getRoleName().equalsIgnoreCase(roleName)
                          )
                  )
                  .orElse(false);
     }
}