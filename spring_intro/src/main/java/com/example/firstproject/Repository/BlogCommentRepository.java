package com.example.firstproject.Repository;

import com.example.firstproject.Model.Entity.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCommentRepository extends JpaRepository<BlogComment, Long> {
}
