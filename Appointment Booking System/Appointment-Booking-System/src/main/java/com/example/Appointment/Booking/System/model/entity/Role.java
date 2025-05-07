package com.example.Appointment.Booking.System.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // "ROLE_USER", "ROLE_DOCTOR"

    // Constructor to initialize only the 'name' field (role name like "ROLE_USER")
    public Role(String name) {
        this.name = name;
    }
}
