package com.example.spring_intro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlogCommentDTO {


    @JsonProperty(namespace ="id")
    private Long id;

    @JsonProperty(namespace ="content")
    private String content;

    @JsonProperty(namespace ="blog_id")
    private Long blogId;

    @JsonProperty(namespace ="view_id")
    private Long viewerId;

    @JsonProperty(namespace ="rating")
    private Double rating;

}
