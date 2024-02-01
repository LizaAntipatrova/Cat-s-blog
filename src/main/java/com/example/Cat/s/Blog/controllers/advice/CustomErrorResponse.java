package com.example.Cat.s.Blog.controllers.advice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}