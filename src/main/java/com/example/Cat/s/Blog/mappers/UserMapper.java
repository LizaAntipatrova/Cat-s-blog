package com.example.Cat.s.Blog.mappers;

import com.example.Cat.s.Blog.controllers.dto.UserDTO;
import com.example.Cat.s.Blog.db.entity.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "numberOfPosts", expression = "java(source.getPosts()!= null ? source.getPosts().size():0)")
    @Mapping(target = "userRole", expression = "java(source.getUserRole().getName().toString())")
    UserDTO userToUserDTO(User source);
}