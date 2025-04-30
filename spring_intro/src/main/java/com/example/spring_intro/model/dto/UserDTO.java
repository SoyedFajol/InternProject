package com.example.spring_intro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name", required = true, defaultValue = "<User>")
    private String username;

    @JsonProperty(value = "email", required = true, defaultValue = "<EMAIL>")
    private String email;

    @JsonProperty(value = "password", required = true, defaultValue = "<PASSWORD>")
    private String password;

    @JsonProperty(value = "phone", required = true, defaultValue = "0000000000")
    private String phone;
}
