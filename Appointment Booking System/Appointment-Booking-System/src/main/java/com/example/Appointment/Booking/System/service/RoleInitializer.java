package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.Role;
import com.example.Appointment.Booking.System.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleInitializer {

    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            // Create "ROLE_USER" if it doesn't exist
            if (!roleRepository.existsByName("ROLE_USER")) {
                roleRepository.save(new Role("ROLE_USER"));
            }
        };
    }
}
