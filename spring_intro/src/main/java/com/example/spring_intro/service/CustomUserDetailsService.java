package com.example.spring_intro.service;

import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserRole;
import com.example.spring_intro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username " + username + " not found"));

        // Convert UserRole objects to SimpleGrantedAuthority and ensure role format
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(this::mapToGrantedAuthority) // Helper method to map roles
                .collect(Collectors.toList());

        // Build and return the UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // Make sure it's properly encoded in the database
                .authorities(authorities)
                .build();
    }

    private SimpleGrantedAuthority mapToGrantedAuthority(UserRole role) {
        // Prefix role name with "ROLE_" for Spring Security compliance
        return new SimpleGrantedAuthority("ROLE_" + role.getRoleName()); // Fixed the field name
    }
}