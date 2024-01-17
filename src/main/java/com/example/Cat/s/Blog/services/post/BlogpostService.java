package com.example.Cat.s.Blog.services.post;

public interface BlogpostService {
    //    мб стоит заменить boolean на void и выбрасывать исключения
    boolean publish(String content, String title, Long authorId);

    boolean edit(Long id, String content, String title);

    boolean delete(Long id);

}
