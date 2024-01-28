package com.example.Cat.s.Blog.services.user;

import com.example.Cat.s.Blog.db.entity.roles.Role;
import com.example.Cat.s.Blog.db.entity.users.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> showById(Long id);

    List<User> showAll();

    boolean add(String username);

    boolean update(Long id, String username, Role userRole);

    boolean delete(Long id);
}
