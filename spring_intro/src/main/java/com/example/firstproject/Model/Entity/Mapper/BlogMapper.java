package com.example.firstproject.Model.Entity.Mapper;

import com.example.firstproject.Model.Entity.DTO.BlogDTO;
import com.example.firstproject.Model.Entity.Blog;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {

    public Blog toEntity(BlogDTO dto) {
        Blog blog = new Blog();
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        return blog;
    }

    public BlogDTO toDTO(Blog blog) {
        BlogDTO dto = new BlogDTO();
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setCreatedAt(blog.getCreatedAt());
        dto.setUpdatedAt(blog.getUpdatedAt());
        return dto;
    }
}
