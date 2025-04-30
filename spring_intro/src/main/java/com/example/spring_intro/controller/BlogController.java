package com.example.spring_intro.controller;

import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody BlogDTO dto) {
        try {
            blogService.save(dto);
            return ResponseEntity.ok("Blog saved successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Failed to create blog: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Blog blog = blogService.findById(id);
            if (blog == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Blog not found with id: " + id);
            }

            BlogDTO blogDTO = blogService.toDTO(blog);
            return ResponseEntity.ok(blogDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving the blog: " + e.getMessage());
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody BlogDTO dto) {
        String response = blogService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean deleted = blogService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Blog deleted successfully");
        } else {
            return ResponseEntity.ok("Blog not found with id: " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<BlogDTO>> getAll() {
        List<BlogDTO> blogs = blogService.getAll();
        return ResponseEntity.ok(blogs);
    }
}
