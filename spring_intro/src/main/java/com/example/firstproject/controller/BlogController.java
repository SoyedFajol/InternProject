package com.example.firstproject.controller;

import com.example.firstproject.model.dto.BlogDTO;
import com.example.firstproject.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBlog(@RequestBody BlogDTO blogDTO) {
        blogService.save(blogDTO);
        return ResponseEntity.ok("Blog created successfully");
    }

    @GetMapping
    public ResponseEntity<List<BlogDTO>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDTO> getBlogById(@PathVariable Long id) {
        return blogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBlog(@PathVariable Long id, @RequestBody BlogDTO blogDTO) {
        if (blogService.update(id, blogDTO)) {
            return ResponseEntity.ok("Blog updated successfully");
        } else {
            return ResponseEntity.status(404).body("Blog not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        if (blogService.delete(id)) {
            return ResponseEntity.ok("Blog deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Blog not found");
        }
    }
}
