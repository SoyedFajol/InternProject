package com.example.spring_intro.service;

import com.example.spring_intro.exception.IllegalException;
import com.example.spring_intro.model.dto.BlogCommentDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.BlogComment;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.mapper.BlogCommentMapper;
import com.example.spring_intro.model.mapper.BlogMapper;
import com.example.spring_intro.repository.BlogCommentRepository;
import com.example.spring_intro.repository.BlogRepository;
import com.example.spring_intro.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogCommentService {

    private final BlogCommentRepository repository;
    private final BlogCommentMapper mapper;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public BlogCommentService(BlogCommentRepository repository, BlogCommentMapper mapper, UserRepository userRepository, BlogRepository blogRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.blogRepository= blogRepository;

    }

    public String save(BlogCommentDTO dto, Long viewerId, Long blogId) {
        try {
            User viewer = userRepository.findById(viewerId)
                    .orElseThrow(() -> new IllegalException("Viewer not found with id: " + viewerId));

            Blog blog = blogRepository.findById(blogId)
                    .orElseThrow(() -> new IllegalException("Blog not found with id: " + blogId));

            BlogComment blogComment = mapper.toEntity(dto, viewer, blog);
            repository.save(blogComment);

            return "Blog comment saved successfully";
        } catch (Exception e) {
            return "Failed to save blog comment: " + e.getMessage();
        }
    }
    public BlogCommentDTO findById(Long id) {
        Optional<BlogComment> comment = repository.findById(id);
        return comment.map(mapper::toDTO).orElse(null);
    }

    public String update(Long id, BlogCommentDTO dto, Long viewerId, Long blogId) {
        try {
            Optional<BlogComment> optionalComment = repository.findById(id);
            if (optionalComment.isPresent()) {
                BlogComment existingComment = optionalComment.get();

                User viewer = userRepository.findById(viewerId)
                        .orElseThrow(() -> new IllegalException("Viewer not found with id: " + viewerId));

                Blog blog = blogRepository.findById(blogId)
                        .orElseThrow(() -> new IllegalException("Blog not found with id: " + blogId));

                existingComment.setContent(dto.getContent());
                existingComment.setViewer(viewer);
                existingComment.setBlog(blog);

                repository.save(existingComment);
                return "Blog comment updated successfully";
            }
            return "Blog comment not found with id: " + id;
        } catch (Exception e) {
            return "Failed to update blog comment: " + e.getMessage();
        }
    }


    public boolean delete(Long id) {
        Optional<BlogComment> comment = repository.findById(id);
        if (comment.isPresent()) {
            repository.delete(comment.get());
            return true;
        }
        return false;
    }


    public List<BlogCommentDTO> getAll() {
        List<BlogComment> comments = repository.findAll();
        return comments.stream().map(mapper::toDTO).toList();
    }

}

