package com.example.Cat.s.Blog.mappers;

import com.example.Cat.s.Blog.controllers.dto.PostDTO;
import com.example.Cat.s.Blog.controllers.dto.TitlePostDTO;
import com.example.Cat.s.Blog.db.entity.posts.Blogpost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BlogpostMapper {

    TitlePostDTO blogpostToTitlePostDTO(Blogpost source);

    @Mapping(target = "author", expression = "java(source.getAuthor().getUsername())")
    PostDTO blogpostToPostDTO(Blogpost source);
}
