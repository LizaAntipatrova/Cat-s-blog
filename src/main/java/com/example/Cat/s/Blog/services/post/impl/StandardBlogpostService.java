package com.example.Cat.s.Blog.services.post.impl;

import com.example.Cat.s.Blog.entity.posts.Blogpost;
import com.example.Cat.s.Blog.entity.repositories.BlogpostRepository;
import com.example.Cat.s.Blog.entity.repositories.UserRepository;
import com.example.Cat.s.Blog.entity.users.User;
import com.example.Cat.s.Blog.services.post.BlogpostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Optional;

public class StandardBlogpostService implements BlogpostService {
    @Autowired
    BlogpostRepository blogpostRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean publish(String content, String title, Long authorId) {
        Optional foundAuthor = userRepository.findById(authorId);
        if (foundAuthor.isPresent()) {
            Date publicationDate = new Date(System.currentTimeMillis());
            Blogpost blogpost = new Blogpost(content, title, (User) foundAuthor.get(), publicationDate);
            blogpostRepository.save(blogpost);
            return true;
        }
        return false;
    }

    @Override
    public boolean edit(Long id, String content, String title) {
        Optional foundBlogpost = blogpostRepository.findById(id);
        if (foundBlogpost.isPresent()) {
            blogpostRepository.save((Blogpost) foundBlogpost.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Optional foundBlogpost = blogpostRepository.findById(id);
        if (foundBlogpost.isPresent()) {
            blogpostRepository.delete((Blogpost) foundBlogpost.get());
            return true;
        }
        return false;
    }
}
