package com.example.Cat.s.Blog.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
public class PostDTO {
    private Long id;
    private String content;
    private String title;

    private String author;
    private Date publicationDate;


}
