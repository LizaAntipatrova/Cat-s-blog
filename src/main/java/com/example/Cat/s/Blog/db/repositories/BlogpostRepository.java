package com.example.Cat.s.Blog.db.repositories;

import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.db.entity.posts.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogpostRepository extends JpaRepository<Blogpost, Long> {
    Blogpost findByAuthor(User author);

    Blogpost findByTitle(String title);
}
