package com.example.firstproject.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("user_id")
    private Long userId;
}