package com.example.Cat.s.Blog.services.exceptions;


public class ExistingUsernameException extends RuntimeException {
    public ExistingUsernameException() {
        super("You cannot add a user with an already existing username");
    }
}
