package com.example.Cat.s.Blog.services.post.impl;

import com.example.Cat.s.Blog.db.entity.posts.Blogpost;
import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.db.repositories.BlogpostRepository;
import com.example.Cat.s.Blog.db.repositories.UserRepository;
import com.example.Cat.s.Blog.services.exceptions.NonExistingBlogpostException;
import com.example.Cat.s.Blog.services.exceptions.NonExistingUserException;
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

    /**
     * возвращает пост по заданнному id
     *
     * @param id
     * @return Blogpost
     */
    @Override
    public Blogpost showById(Long id) {
        Optional<Blogpost> foundBlogpost = blogpostRepository.findById(id);
        if (foundBlogpost.isEmpty()) {
            throw new NonExistingBlogpostException();
        }
        return foundBlogpost.get();
    }

    /**
     * возвращает список всех постов
     *
     * @return список всех постов
     */
    @Override
    public List<Blogpost> showAll() {
        return blogpostRepository.findAll();
    }

    /**
     * Публикует пост, помещает в БД
     *
     * @param content  - содержание поста
     * @param title    - заголовок поста
     * @param authorId - id автора поста
     */
    @Override
    public void publish(String content, String title, Long authorId) {
        //а к автору не надо прихуярить?
        Optional<User> foundAuthor = userRepository.findById(authorId);
        if (foundAuthor.isEmpty()) {
            throw new NonExistingUserException();
        }
        Date publicationDate = new Date(System.currentTimeMillis());
        Blogpost blogpost = new Blogpost(content, title, foundAuthor.get(), publicationDate);
        blogpostRepository.saveAndFlush(blogpost);
    }

    /**
     * редактирует заголовок и содержание поста
     *
     * @param id
     * @param content
     * @param title
     */
    @Override
    public void edit(Long id, String content, String title) {
        Optional<Blogpost> foundBlogpost = blogpostRepository.findById(id);
        if (foundBlogpost.isEmpty()) {
            throw new NonExistingBlogpostException();
        }
        Blogpost blogpost = foundBlogpost.get();
        blogpost.setTitle(title);
        blogpost.setContent(content);
        blogpostRepository.saveAndFlush(blogpost);
    }

    /**
     * удаляет пост из БД
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        Optional<Blogpost> foundBlogpost = blogpostRepository.findById(id);
        if (foundBlogpost.isEmpty()) {
            throw new NonExistingBlogpostException();
        }
        blogpostRepository.delete(foundBlogpost.get());

    }
}
