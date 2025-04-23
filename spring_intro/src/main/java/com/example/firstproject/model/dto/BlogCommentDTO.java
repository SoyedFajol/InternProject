package com.example.firstproject.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlogCommentDTO {

    @JsonProperty("comment")
    private String comment;


    @JsonProperty("book_id")
    private Long bookId;
}
