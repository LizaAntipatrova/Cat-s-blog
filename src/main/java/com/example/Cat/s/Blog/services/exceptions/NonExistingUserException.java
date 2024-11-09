package com.example.Cat.s.Blog.services.exceptions;

public class NonExistingUserException extends RuntimeException {
    public NonExistingUserException() {
        super("User is not found");
    }
}
