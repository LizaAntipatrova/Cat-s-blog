package com.example.Cat.s.Blog.services.post.impl;

import com.example.Cat.s.Blog.db.entity.posts.Blogpost;
import com.example.Cat.s.Blog.db.repositories.BlogpostRepository;
import com.example.Cat.s.Blog.db.repositories.UserRepository;
import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.services.post.BlogpostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StandardBlogpostService implements BlogpostService {

    private final BlogpostRepository blogpostRepository;

    private final UserRepository userRepository;


    @Override
    public Optional<Blogpost> showById(Long id){
        return blogpostRepository.findById(id);
    }

    @Override
    public List<Blogpost> showAll(){
        return blogpostRepository.findAll();
    }

    @Override
    public boolean publish(String content, String title, Long authorId) {
        Optional<User> foundAuthor = userRepository.findById(authorId);
        if (foundAuthor.isPresent()) {
            Date publicationDate = new Date(System.currentTimeMillis());
            Blogpost blogpost = new Blogpost(content, title, foundAuthor.get(), publicationDate);
            blogpostRepository.saveAndFlush(blogpost);
            return true;
        }
        return false;
    }

    @Override
    public boolean edit(Long id, String content, String title) {
        Optional<Blogpost> foundBlogpost = blogpostRepository.findById(id);
        if (foundBlogpost.isPresent()) {
            Blogpost blogpost = foundBlogpost.get();
            blogpost.setTitle(title);
            blogpost.setContent(content);
            blogpostRepository.saveAndFlush(blogpost);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Blogpost> foundBlogpost = blogpostRepository.findById(id);
        if (foundBlogpost.isPresent()) {
            blogpostRepository.delete(foundBlogpost.get());
            return true;
        }
        return false;
    }
}
