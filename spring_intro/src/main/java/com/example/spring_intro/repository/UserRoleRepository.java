package com.example.spring_intro.repository;

import com.example.spring_intro.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    // Fetch role by its name
    Optional<UserRole> findByRoleName(String roleName);
}