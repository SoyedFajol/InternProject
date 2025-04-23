package com.example.firstproject.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_role")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRole {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    private String roleName;
    @ManyToMany
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    @JsonIgnore
    private Set<User> users;
    private String description;

}
