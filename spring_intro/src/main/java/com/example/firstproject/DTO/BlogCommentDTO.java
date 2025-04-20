package com.example.firstproject.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlogCommentDTO {

    @JsonProperty(value = "blog_comment", required = true, defaultValue = "Write here comments...")
    private String comment;
}
