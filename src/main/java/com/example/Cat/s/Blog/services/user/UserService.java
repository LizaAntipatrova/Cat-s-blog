package com.example.Cat.s.Blog.services.user;

import com.example.Cat.s.Blog.db.entity.roles.Role;
import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.services.exceptions.NonExistingUserException;

import java.util.List;

public interface UserService {

    User showById(Long id);

    List<User> showAll();

    void add(String username, String password);

    void update(Long id, String username, Role userRole) throws NonExistingUserException;

    void delete(Long id);
}
