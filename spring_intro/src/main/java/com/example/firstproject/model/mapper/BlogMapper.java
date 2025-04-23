package com.example.firstproject.model.mapper;

import com.example.firstproject.model.dto.BlogDTO;
import com.example.firstproject.model.entity.Blog;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {

    public Blog toEntity(BlogDTO dto) {
        Blog blog = new Blog();
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setRating(dto.getRating());
        return blog;
    }

    public BlogDTO toDTO(Blog blog) {
        BlogDTO dto = new BlogDTO();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setCreatedAt(blog.getCreatedAt());
        dto.setUpdatedAt(blog.getUpdatedAt());
        dto.setRating(blog.getRating());

        if (blog.getAuthor() != null) {
            dto.setAuthorUserId(blog.getAuthor().getId());
        }

        return dto;
    }
}