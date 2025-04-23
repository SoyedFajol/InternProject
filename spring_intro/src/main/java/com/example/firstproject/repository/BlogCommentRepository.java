package com.example.firstproject.repository;

import com.example.firstproject.model.entity.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCommentRepository extends JpaRepository<BlogComment, Long> {
}
