package com.example.firstproject.service;

import com.example.firstproject.model.dto.BlogCommentDTO;
import com.example.firstproject.model.entity.BlogComment;
import com.example.firstproject.model.entity.Book;
import com.example.firstproject.model.mapper.BlogCommentMapper;
import com.example.firstproject.repository.BlogCommentRepository;
import com.example.firstproject.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogCommentService {

    private final BlogCommentRepository repository;
    private final BlogCommentMapper mapper;
    private final BookRepository bookRepository;

    public BlogCommentService(BlogCommentRepository repository, BlogCommentMapper mapper, BookRepository bookRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.bookRepository = bookRepository;
    }

    public boolean save(BlogCommentDTO dto) {
        Optional<Book> bookOpt = bookRepository.findById(dto.getBookId());
        if (bookOpt.isPresent()) {
            BlogComment comment = mapper.toEntity(dto, bookOpt.get());
            repository.save(comment);
            return true;
        }
        return false;
    }

    public List<BlogCommentDTO> getAll() {
        List<BlogComment> comments = repository.findAll();
        return comments.stream().map(mapper::toDTO).toList();
    }

    public Optional<BlogCommentDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public boolean update(Long id, BlogCommentDTO dto) {
        Optional<BlogComment> commentOpt = repository.findById(id);
        if (commentOpt.isPresent()) {
            BlogComment comment = commentOpt.get();
            comment.setComment(dto.getComment());
            repository.save(comment);
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
