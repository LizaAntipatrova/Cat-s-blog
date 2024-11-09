package com.example.Cat.s.Blog.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
public class TitlePostDTO {
    private Long id;
    private String title;
    private Date publicationDate;


}
