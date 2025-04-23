package com.example.firstproject.model.mapper;

import com.example.firstproject.model.dto.BlogCommentDTO;
import com.example.firstproject.model.entity.BlogComment;
import com.example.firstproject.model.entity.Book;
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
