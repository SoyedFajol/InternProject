package com.example.spring_intro.service;

import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User not found with id: " + id));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("User not found with username: " + username));
    }

    public void update(Long id, User updatedUser) {
        User user = findById(id);
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}