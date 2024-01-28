package com.example.Cat.s.Blog.controllers.user;

import com.example.Cat.s.Blog.controllers.dto.TitlePostDTO;
import com.example.Cat.s.Blog.controllers.dto.UserDTO;
import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.mappers.BlogpostMapper;
import com.example.Cat.s.Blog.mappers.UserMapper;
import com.example.Cat.s.Blog.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
class UserRestController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final BlogpostMapper blogpostMapper;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.showAll();
        return users.stream().map(userMapper::userToUserDTO).toList();
    }


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public PostDto createPost(@RequestBody PostDto postDto) {
//        Post post = convertToEntity(postDto);
//        Post postCreated = postService.createPost(post));
//        return convertToDto(postCreated);
//    }

    @GetMapping(value = "/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.showById(id);
        if (user.isPresent()) {
            return userMapper.userToUserDTO(user.get());
        }
        return new UserDTO();

    }

    @GetMapping(value = "/{id}/posts")
    public List<TitlePostDTO> getUsersPostsByUserId(@PathVariable("id") Long id) {
        var user = userService.showById(id);
        return user
                .map(value -> value.getPosts()
                .stream()
                .map(blogpostMapper::blogpostToTitlePostDTO).toList())
                .orElseGet(ArrayList::new);

    }

//    @PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updatePost(@PathVariable("id") Long id, @RequestBody PostDto postDto) {
//        if(!Objects.equals(id, postDto.getId())){
//            throw new IllegalArgumentException("IDs don't match");
//        }
//        Post post = convertToEntity(postDto);
//        postService.updatePost(post);
//    }
}