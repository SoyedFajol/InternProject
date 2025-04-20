package com.example.firstproject.Mapper;

import com.example.firstproject.DTO.BlogDTO;
import com.example.firstproject.Entity.Blog;
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
        return dto;
    }
}
