package com.example.Cat.s.Blog.mappers;

import com.example.Cat.s.Blog.controllers.dto.UserDTO;
import com.example.Cat.s.Blog.db.entity.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
/**
 * преобразует сущность пользователя в DTO
 * https://habr.com/ru/articles/513072/
 * (DTO - Объект для переноса данных, например между клиентом и сервером)
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    //Используют аннотацию если типы и имена несовпадают в сущности и DTO, или надо их специфично преобразовать
    @Mapping(target = "numberOfPosts", expression = "java(source.getPosts()!= null ? source.getPosts().size():0)")
    @Mapping(target = "userRole", expression = "java(source.getUserRole().getName().toString())")
    UserDTO userToUserDTO(User source);
}