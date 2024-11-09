package com.example.Cat.s.Blog.services.exceptions;

public class NonExistingBlogpostException extends RuntimeException {
    public NonExistingBlogpostException() {
        super("Blogpost is not found");
    }
}
