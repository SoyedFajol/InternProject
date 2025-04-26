package com.example.spring_intro.repository;

import com.example.spring_intro.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
