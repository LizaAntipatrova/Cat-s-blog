package com.example.Cat.s.Blog.entity.repositories;

import com.example.Cat.s.Blog.entity.posts.Blogpost;
import com.example.Cat.s.Blog.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogpostRepository extends JpaRepository<Blogpost, Long> {
    Blogpost findByAuthor(User author);

    Blogpost findByTitle(String title);
}
