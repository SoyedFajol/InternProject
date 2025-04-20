package com.example.firstproject.Service;

import com.example.firstproject.DTO.BlogDTO;
import com.example.firstproject.Entity.Blog;
import com.example.firstproject.Mapper.BlogMapper;
import com.example.firstproject.Repository.BlogRepository;
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
        return blogs.stream().map(blogMapper::toDTO).toList();
    }

    public Optional<BlogDTO> findById(Long id) {
        return blogRepository.findById(id).map(blogMapper::toDTO);
    }

    public boolean update(Long id, BlogDTO blogDTO) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (blog.isPresent()) {
            Blog updatedBlog = blog.get();
            updatedBlog.setTitle(blogDTO.getTitle());
            updatedBlog.setContent(blogDTO.getContent());
            blogRepository.save(updatedBlog);
            return true;
        }
        return false;
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
