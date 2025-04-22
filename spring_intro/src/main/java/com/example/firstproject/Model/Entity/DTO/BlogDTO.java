package com.example.firstproject.Model.Entity.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogDTO {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
