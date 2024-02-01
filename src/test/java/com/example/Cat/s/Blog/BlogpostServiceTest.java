package com.example.Cat.s.Blog;

import com.example.Cat.s.Blog.db.entity.posts.Blogpost;
import com.example.Cat.s.Blog.db.entity.roles.Role;
import com.example.Cat.s.Blog.db.entity.roles.RoleType;
import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.db.repositories.BlogpostRepository;
import com.example.Cat.s.Blog.db.repositories.RoleRepository;
import com.example.Cat.s.Blog.db.repositories.UserRepository;
import com.example.Cat.s.Blog.services.exceptions.NonExistingBlogpostException;
import com.example.Cat.s.Blog.services.exceptions.NonExistingUserException;
import com.example.Cat.s.Blog.services.post.impl.StandardBlogpostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
class BlogpostServiceTest {
    private final String titleNonSaved = "CatNoName";
    private final String titleSaved = "Cat";
    private final String content = "Cat is very cute!";
    private final Role role = new Role(RoleType.USER);
    private final User user = new User("username", role);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogpostRepository blogpostRepository;
    @Autowired
    private StandardBlogpostService blogpostService;
    @Autowired
    private RoleRepository roleRepository;
    private Blogpost blogpost;

    @BeforeEach
    public void setup() {
        roleRepository.saveAndFlush(role);
        userRepository.saveAndFlush(user);
        blogpost = new Blogpost("Cat is very cute!",
                titleSaved,
                user,
                new Date(System.currentTimeMillis()));
        blogpostRepository.saveAndFlush(blogpost);
    }

    @AfterEach
    public void cleanUp() {
        blogpostRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    public void showByIdTestWhenPostExistedThenGetPost() {
        Assertions.assertEquals(blogpostRepository.findById(blogpost.getId()).get(), blogpostService.showById(blogpost.getId()));
    }

    @Test
    public void showByIdTestWhenPostNonExistedThenNonExistingBlogpostException() {
        Assertions.assertThrows(NonExistingBlogpostException.class,
                () -> blogpostService.showById(Long.MAX_VALUE - 1));
    }


    @Test
    public void publishTestWhenPostAddedThenPostWithThatTitleAppearsInRepository() {
        blogpostService.publish(content, titleNonSaved, user.getId());
        Assertions.assertNotNull(blogpostRepository.findByTitle(titleNonSaved));
    }

    @Test
    public void publishTestWhenNonExistingUserPublishPostThenNonExistingUserException() {
        Assertions.assertThrows(NonExistingUserException.class,
                () -> blogpostService.publish(content, titleNonSaved, Long.MAX_VALUE - 1));
    }


    @Test
    public void editTestWhenEditPostThenNumberOfRecordsInRepositoryNotChange() {
        Long idSaved = blogpost.getId();
        blogpostService.edit(idSaved, content, titleNonSaved);
        Assertions.assertEquals(userRepository.count(), 1);
    }

    @Test
    public void editTestWhenEditPostThenInformationChange() {
        Long idSaved = blogpost.getId();
        Assertions.assertEquals(blogpostRepository.findById(idSaved).get().getTitle(), titleSaved);
        blogpostService.edit(idSaved, content, titleNonSaved);
        Assertions.assertEquals(blogpostRepository.findById(idSaved).get().getTitle(), titleNonSaved);
    }

    @Test
    public void editTestWhenEditPostWithIdThatNotInRepositoryThenNonExistingBlogpostException() {
        Assertions.assertThrows(NonExistingBlogpostException.class,
                () -> blogpostService.edit(Long.MAX_VALUE - 1, content, titleNonSaved));
    }


    @Test
    public void deleteTestWhenDeletePostNumberOfRecordsInRepositoryDecreasing() {
        Long idSaved = blogpost.getId();
        Assertions.assertEquals(blogpostRepository.count(), 1);
        blogpostService.delete(idSaved);
        Assertions.assertEquals(blogpostRepository.count(), 0);
    }

    @Test
    public void deleteTestWhenDeletePostWithIdThatNotInRepositoryThenNonExistingBlogpostException() {
        Assertions.assertThrows(NonExistingBlogpostException.class,
                () -> blogpostService.delete(Long.MAX_VALUE - 1));
    }
}