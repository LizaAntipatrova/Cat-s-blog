package com.example.Cat.s.Blog;

import com.example.Cat.s.Blog.db.repositories.RoleRepository;
import com.example.Cat.s.Blog.db.repositories.UserRepository;
import com.example.Cat.s.Blog.db.entity.roles.Role;
import com.example.Cat.s.Blog.db.entity.roles.RoleType;
import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.services.user.impl.StandardUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    private final String usernameSaved = "username000";
    private final String usernameNonSaved = "username";
    private final Role role = new Role(RoleType.USER);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StandardUserService userService;

    private User user;

    @BeforeEach
    public void setup() {
        roleRepository.saveAndFlush(role);

        user = new User(usernameSaved, role);
        userRepository.saveAndFlush(user);
    }

    @AfterEach
    public void cleanUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();

    }

    @Test
    public void addTestWhenUserAddedThenUserWithThatUsernameAppearsInRepository() {
        userService.add(usernameNonSaved);
        Assertions.assertNotNull(userRepository.findByUsername(usernameNonSaved));
    }

    @Test
    public void addTestWhenUserWithNameThatNotInRepositoryAddedThenTrue() {
        Assertions.assertTrue(userService.add(usernameNonSaved));
    }

    @Test
    public void addTestWhenUserWithNameThatInRepositoryAddedThenFalse() {
        Assertions.assertFalse(userService.add(usernameSaved));

    }


    @Test
    public void updateTestWhenUpdateUserThenNumberOfRecordsInRepositoryNotChange() {
        Long idSaved = user.getId();
        userService.update(idSaved, usernameNonSaved, role);
        Assertions.assertEquals(userRepository.count(), 1);
    }

    @Test
    public void updateTestWhenUpdateUserThenInformationChange() {
        Long idSaved = user.getId();
        Assertions.assertEquals(userRepository.findById(idSaved).get().getUsername(), usernameSaved);
        userService.update(idSaved, usernameNonSaved, role);
        Assertions.assertEquals(userRepository.findById(idSaved).get().getUsername(), usernameNonSaved);
    }

    @Test
    public void updateTestWhenUpdateUserWithIdThatInRepositoryThenTrue() {
        Long idSaved = user.getId();
        Assertions.assertTrue(userService.update(idSaved, usernameNonSaved, role));
    }

    @Test
    public void updateTestWhenUpdateUserWithIdThatNotInRepositoryThenFalse() {
        Assertions.assertFalse(userService.update(Long.MAX_VALUE - 1, usernameNonSaved, role));
    }


    @Test
    public void deleteTestWhenDeleteUserNumberOfRecordsInRepositoryDecreasing() {
        Long idSaved = user.getId();
        Assertions.assertEquals(userRepository.count(), 1);
        userService.delete(idSaved);
        Assertions.assertEquals(userRepository.count(), 0);
    }

    @Test
    public void deleteTestWhenDeleteUserWithIdThatInRepositoryThenTrue() {
        Long idSaved = user.getId();
        Assertions.assertTrue(userService.delete(idSaved));
    }

    @Test
    public void deleteTestWhenDeleteUserWithIdThatNotInRepositoryThenFalse() {
        Assertions.assertFalse(userService.delete(Long.MAX_VALUE - 1));
    }

}
