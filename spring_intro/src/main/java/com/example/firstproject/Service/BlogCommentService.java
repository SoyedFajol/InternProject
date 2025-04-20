package com.example.firstproject.Service;

import com.example.firstproject.DTO.BlogCommentDTO;
import com.example.firstproject.Entity.BlogComment;
import com.example.firstproject.Entity.Book;
import com.example.firstproject.Mapper.BlogCommentMapper;
import com.example.firstproject.Repository.BlogCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogCommentService {

    private final BlogCommentRepository repository;
    private final BlogCommentMapper mapper;

    public BlogCommentService(BlogCommentRepository repository, BlogCommentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(BlogCommentDTO dto) {
        // Implement Book fetching logic here
        Book viewer = new Book();
        BlogComment comment = mapper.toEntity(dto, viewer);
        repository.save(comment);
    }

    public List<BlogCommentDTO> getAll() {
        List<BlogComment> comments = repository.findAll();
        return comments.stream().map(mapper::toDTO).toList();
    }

    public Optional<BlogCommentDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public boolean update(Long id, BlogCommentDTO dto) {
        Optional<BlogComment> comment = repository.findById(id);
        if (comment.isPresent()) {
            BlogComment updatedComment = comment.get();
            updatedComment.setComment(dto.getComment());
            repository.save(updatedComment);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        Optional<BlogComment> comment = repository.findById(id);
        if (comment.isPresent()) {
            repository.delete(comment.get());
            return true;
        }
        return false;
    }
}
