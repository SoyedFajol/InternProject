package com.example.spring_intro.controller;

import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.service.BlogService;
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
    public ResponseEntity<String> save(@RequestBody BlogDTO dto) {
        blogService.save(dto);
        return ResponseEntity.ok("Blog saved successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getById(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(blog);
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
