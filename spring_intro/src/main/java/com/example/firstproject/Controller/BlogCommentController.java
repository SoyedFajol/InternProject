package com.example.firstproject.Controller;

import com.example.firstproject.DTO.BlogCommentDTO;
import com.example.firstproject.Service.BlogCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class BlogCommentController {

    private final BlogCommentService blogCommentService;

    public BlogCommentController(BlogCommentService blogCommentService) {
        this.blogCommentService = blogCommentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createComment(@RequestBody BlogCommentDTO blogCommentDTO) {
        blogCommentService.save(blogCommentDTO);
        return ResponseEntity.ok("Comment created successfully");
    }

    @GetMapping
    public ResponseEntity<List<BlogCommentDTO>> getAllComments() {
        return ResponseEntity.ok(blogCommentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogCommentDTO> getCommentById(@PathVariable Long id) {
        return blogCommentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateComment(@PathVariable Long id, @RequestBody BlogCommentDTO blogCommentDTO) {
        if (blogCommentService.update(id, blogCommentDTO)) {
            return ResponseEntity.ok("Comment updated successfully");
        } else {
            return ResponseEntity.status(404).body("Comment not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        if (blogCommentService.delete(id)) {
            return ResponseEntity.ok("Comment deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Comment not found");
        }
    }
}
