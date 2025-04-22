package com.example.firstproject.Model.Entity.Mapper;

import com.example.firstproject.Model.Entity.DTO.BlogCommentDTO;
import com.example.firstproject.Model.Entity.BlogComment;
import com.example.firstproject.Model.Entity.Book;
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
        dto.setBookId(entity.getViewer().getId());
        return dto;
    }
}
