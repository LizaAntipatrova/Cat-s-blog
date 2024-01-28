package com.example.Cat.s.Blog;

import com.example.Cat.s.Blog.db.entity.posts.Blogpost;
import com.example.Cat.s.Blog.db.repositories.BlogpostRepository;
import com.example.Cat.s.Blog.db.repositories.RoleRepository;
import com.example.Cat.s.Blog.db.repositories.UserRepository;
import com.example.Cat.s.Blog.db.entity.roles.Role;
import com.example.Cat.s.Blog.db.entity.roles.RoleType;
import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.services.post.impl.StandardBlogpostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
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
                "Cat",
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
    public void publishTestWhenPostAddedThenPostWithThatTitleAppearsInRepository() {
        blogpostService.publish(content, titleNonSaved, user.getId());
        Assertions.assertNotNull(blogpostRepository.findByTitle(titleNonSaved));
    }

    @Test
    public void publishTestWhenExistingUserPublishPostThenTrue() {
        Assertions.assertTrue(blogpostService.publish(content, titleNonSaved, user.getId()));
    }

    @Test
    public void publishTestWhenNonExistingUserPublishPostThenFalse() {
        Assertions.assertFalse(blogpostService.publish(content, titleNonSaved, Long.MAX_VALUE - 1));
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
    public void editTestWhenEditPostWithIdThatInRepositoryThenTrue() {
        Long idSaved = blogpost.getId();
        Assertions.assertTrue(blogpostService.edit(idSaved, content, titleNonSaved));
    }

    @Test
    public void editTestWhenEditPostWithIdThatNotInRepositoryThenFalse() {
        Assertions.assertFalse(blogpostService.edit(Long.MAX_VALUE - 1, content, titleNonSaved));
    }


    @Test
    public void deleteTestWhenDeletePostNumberOfRecordsInRepositoryDecreasing() {
        Long idSaved = blogpost.getId();
        Assertions.assertEquals(blogpostRepository.count(), 1);
        blogpostService.delete(idSaved);
        Assertions.assertEquals(blogpostRepository.count(), 0);
    }

    @Test
    public void deleteTestWhenDeletePostWithIdThatInRepositoryThenTrue() {
        Long idSaved = blogpost.getId();
        Assertions.assertTrue(blogpostService.delete(idSaved));
    }

    @Test
    public void deleteTestWhenDeletePostWithIdThatNotInRepositoryThenFalse() {
        Assertions.assertFalse(blogpostService.delete(Long.MAX_VALUE - 1));
    }
}