package com.example.spring_intro.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String UserName;
    private String password;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "author")
    private List<Blog> blogs;

    @OneToMany(mappedBy = "viewer")
    private List<BlogComment> comments;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private Set<UserRole> roles = new HashSet<>();
}
