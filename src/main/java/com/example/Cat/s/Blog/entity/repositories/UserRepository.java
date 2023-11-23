package com.example.Cat.s.Blog.entity.repositories;

import com.example.Cat.s.Blog.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
