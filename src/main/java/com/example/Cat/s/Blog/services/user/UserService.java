package com.example.Cat.s.Blog.services.user;

import com.example.Cat.s.Blog.entity.roles.Role;

public interface UserService {

    boolean add(String username);

    boolean update(Long id, String username, Role userRole);

    boolean delete(Long id);
}
