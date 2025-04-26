package com.example.spring_intro.repository;

import com.example.spring_intro.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
