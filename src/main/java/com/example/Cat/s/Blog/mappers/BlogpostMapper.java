package com.example.Cat.s.Blog.mappers;

import com.example.Cat.s.Blog.controllers.dto.PostDTO;
import com.example.Cat.s.Blog.controllers.dto.TitlePostDTO;
import com.example.Cat.s.Blog.db.entity.posts.Blogpost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * преобразует сущность пост в DTO
 * https://habr.com/ru/articles/513072/
 * (DTO - Объект для переноса данных, например между клиентом и сервером)
 */
@Mapper(componentModel = "spring")
public interface BlogpostMapper {

    TitlePostDTO blogpostToTitlePostDTO(Blogpost source);

    //Используют аннотацию если типы и имена несовпадают в сущности и DTO, или надо их специфично преобразовать
    @Mapping(target = "author", expression = "java(source.getAuthor().getUsername())")
    PostDTO blogpostToPostDTO(Blogpost source);
}
