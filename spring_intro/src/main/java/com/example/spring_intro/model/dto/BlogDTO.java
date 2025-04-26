package com.example.spring_intro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogDTO {

    @JsonProperty(namespace ="id")
    private Long id;

    @JsonProperty(namespace ="title")
    private String title;

    @JsonProperty(namespace ="content")
    private String content;

    @JsonProperty(namespace ="author_user_id")
    private Long authorUserId;

    @JsonProperty(namespace ="createdAt")
    private LocalDateTime createdAt;

    @JsonProperty(namespace ="updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty(namespace ="rating")
    private Double rating;
}
