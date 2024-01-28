package com.example.Cat.s.Blog.db.repositories;

import com.example.Cat.s.Blog.db.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
