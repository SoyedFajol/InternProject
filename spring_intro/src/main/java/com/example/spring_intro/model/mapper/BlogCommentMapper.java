package com.example.spring_intro.model.mapper;

import com.example.spring_intro.model.dto.BlogCommentDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.BlogComment;
import com.example.spring_intro.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class BlogCommentMapper {

    public BlogComment toEntity(BlogCommentDTO dto, User viewer, Blog blog) {
        BlogComment entity = new BlogComment();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setViewer(viewer);
        entity.setBlog(blog);
        return entity;
    }


    public BlogCommentDTO toDTO(BlogComment entity) {
        BlogCommentDTO dto = new BlogCommentDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setBlogId(entity.getBlog().getId());
        dto.setViewerId(entity.getViewer().getId());
        return dto;
    }
}
