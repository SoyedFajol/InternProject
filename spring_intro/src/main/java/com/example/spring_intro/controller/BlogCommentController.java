package com.example.spring_intro.controller;

import com.example.spring_intro.model.dto.BlogCommentDTO;
import com.example.spring_intro.service.BlogCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class BlogCommentController {

    private final BlogCommentService blogCommentService;

    public BlogCommentController(BlogCommentService blogCommentService) {
        this.blogCommentService = blogCommentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> save(@RequestBody BlogCommentDTO dto,
                                       @RequestParam Long viewerId,
                                       @RequestParam Long blogId) {
        String response = blogCommentService.save(dto, viewerId, blogId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogCommentDTO> getById(@PathVariable Long id) {
        BlogCommentDTO comment = blogCommentService.findById(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @RequestBody BlogCommentDTO dto,
                                         @RequestParam Long viewerId,
                                         @RequestParam Long blogId) {
        String response = blogCommentService.update(id, dto, viewerId, blogId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean deleted = blogCommentService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Comment deleted successfully");
        } else {
            return ResponseEntity.ok("Comment not found with id: " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<BlogCommentDTO>> getAll() {
        List<BlogCommentDTO> comments = blogCommentService.getAll();
        return ResponseEntity.ok(comments);
    }
}
