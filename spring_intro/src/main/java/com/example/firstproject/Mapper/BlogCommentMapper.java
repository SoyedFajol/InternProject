package com.example.firstproject.Mapper;

import com.example.firstproject.DTO.BlogCommentDTO;
import com.example.firstproject.Entity.BlogComment;
import com.example.firstproject.Entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BlogCommentMapper {

    public BlogComment toEntity(BlogCommentDTO dto, Book viewer) {
        BlogComment entity = new BlogComment();
        entity.setViewer(viewer);
        entity.setComment(dto.getComment());
        return entity;
    }

    public BlogCommentDTO toDTO(BlogComment entity) {
        BlogCommentDTO dto = new BlogCommentDTO();
        dto.setComment(entity.getComment());
        return dto;
    }
}
