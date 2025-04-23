package com.example.firstproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogDTO {

    @JsonProperty(namespace = "id")
    private Long id;

    @JsonProperty(namespace = "author_user_id")
    private Long authorUserId;

    @JsonProperty(value = "title",required = true, defaultValue = "default title")
    private String title;

    @JsonProperty(value = "content",required = true, defaultValue = "default content")
    private String content;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;


    @JsonProperty(value = "rating")
    private Double rating;
}
