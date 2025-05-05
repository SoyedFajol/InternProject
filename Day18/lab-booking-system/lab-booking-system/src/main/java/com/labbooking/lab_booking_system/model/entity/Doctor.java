package com.labbooking.lab_booking_system.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String degree;

    private String designation;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
}