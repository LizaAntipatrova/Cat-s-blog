package com.example.Cat.s.Blog.services.post;

import com.example.Cat.s.Blog.db.entity.posts.Blogpost;

import java.util.List;

public interface BlogpostService {
    Blogpost showById(Long id);

    List<Blogpost> showAll();

    //    мб стоит заменить boolean на void и выбрасывать исключения
    void publish(String content, String title, Long authorId);

    void edit(Long id, String content, String title);

    void delete(Long id);

}
