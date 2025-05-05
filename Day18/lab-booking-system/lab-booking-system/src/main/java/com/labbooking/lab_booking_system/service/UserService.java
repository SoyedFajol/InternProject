package com.labbooking.lab_booking_system.service;

import com.labbooking.lab_booking_system.model.dto.UserDTO;
import com.labbooking.lab_booking_system.model.entity.Role;
import com.labbooking.lab_booking_system.model.entity.User;
import com.labbooking.lab_booking_system.model.mapper.UserMapper;
import com.labbooking.lab_booking_system.repository.RoleRepository;
import com.labbooking.lab_booking_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByMobileNumber(userDTO.getMobileNumber())) {
            throw new RuntimeException("Mobile number already exists");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        User user = UserMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(userRole));

        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDto(user);
    }
}
