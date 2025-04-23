package com.example.firstproject.service;

import com.example.firstproject.model.dto.BlogDTO;
import com.example.firstproject.model.entity.Blog;
import com.example.firstproject.model.entity.User;
import com.example.firstproject.model.mapper.BlogMapper;
import com.example.firstproject.repository.BlogRepository;
import com.example.firstproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final UserRepository userRepository;
    private final UserRoleService roleService;

    public BlogService(BlogRepository blogRepository, BlogMapper blogMapper,
                       UserRepository userRepository, UserRoleService roleService) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public String save(BlogDTO dto) {
        Optional<User> user = userRepository.findById(dto.getAuthorUserId());
        if (user.isEmpty()) {
            return "User not found with given ID";
        }

        if (!roleService.isCreateBlogAccessAuthority(dto.getAuthorUserId())) {
            return "You are not authorized to create a blog";
        }

        Blog blog = blogMapper.toEntity(dto);
        blog.setAuthor(user.get());

        blogRepository.save(blog);
        return "Blog created successfully";
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