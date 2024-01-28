package com.example.Cat.s.Blog.services.post;

import com.example.Cat.s.Blog.db.entity.posts.Blogpost;

import java.util.List;
import java.util.Optional;

public interface BlogpostService {
    Optional<Blogpost> showById(Long id);

    List<Blogpost> showAll();

    //    мб стоит заменить boolean на void и выбрасывать исключения
    boolean publish(String content, String title, Long authorId);

    boolean edit(Long id, String content, String title);

    boolean delete(Long id);

}
