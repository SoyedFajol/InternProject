package com.example.spring_intro.model.mapper;

import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {

    private final UserService userService;

    public BlogMapper(UserService userService) {
        this.userService = userService;
    }

    public Blog toEntity(BlogDTO dto) {
        Blog blog = new Blog();
        blog.setId(dto.getId());
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setRating(dto.getRating());
        User user = userService.findById(dto.getAuthorUserId());
        if(user != null) {
            blog.setAuthor(user);
        }else
        {
            throw new IllegalArgumentException("User not found");
        }
        return blog;
    }

    public BlogDTO toDTO(Blog blog) {
        BlogDTO dto = new BlogDTO();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setCreatedAt(blog.getCreatedAt());
        dto.setUpdatedAt(blog.getUpdatedAt());
        dto.setAuthorUserId(blog.getAuthor().getId());
        dto.setRating(blog.getRating());

        return dto;
    }
}
