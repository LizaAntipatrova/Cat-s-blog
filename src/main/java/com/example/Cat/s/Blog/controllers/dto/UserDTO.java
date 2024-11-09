package com.example.Cat.s.Blog.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;

    private String username;

    private String userRole;
    private int numberOfPosts;

}
