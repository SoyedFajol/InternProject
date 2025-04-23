package com.example.firstproject.repository;

import com.example.firstproject.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByRoleName(String roleName);

    @Query("SELECT r FROM UserRole r JOIN r.users u WHERE u.id = :userId")
    Optional<UserRole> findByUserId(@Param("userId") Long userId);
}