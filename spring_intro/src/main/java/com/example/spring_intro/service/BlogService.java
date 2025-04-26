package com.example.spring_intro.service;

import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.mapper.BlogMapper;
import com.example.spring_intro.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    public BlogService(BlogRepository blogRepository, BlogMapper blogMapper) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
    }

    public void save(BlogDTO blogDTO) {
        Blog blog = blogMapper.toEntity(blogDTO);
        blogRepository.save(blog);
    }

    public List<BlogDTO> getAll() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(blogMapper::toDTO)
                .toList();
    }

    public Blog findById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    public String update(Long id, BlogDTO blogDTO) {
        try {
            Blog existingBlog = blogRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Blog not found with id: " + id));

            Blog updatedBlog = blogMapper.toEntity(blogDTO);
            updatedBlog.setId(existingBlog.getId());
            blogRepository.save(updatedBlog);
            return "Blog updated successfully with id: " + id;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public boolean delete(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (blog.isPresent()) {
            blogRepository.delete(blog.get());
            return true;
        }
        return false;
    }
}
