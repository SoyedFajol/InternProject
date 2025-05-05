package com.labbooking.lab_booking_system.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role")
    private Set<Doctor> doctors;
}