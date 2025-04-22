package com.example.firstproject.Repository;

import com.example.firstproject.Model.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
