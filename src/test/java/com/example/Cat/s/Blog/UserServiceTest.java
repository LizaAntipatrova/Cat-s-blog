package com.example.Cat.s.Blog;

import com.example.Cat.s.Blog.entity.repositories.RoleRepository;
import com.example.Cat.s.Blog.entity.repositories.UserRepository;
import com.example.Cat.s.Blog.entity.roles.Role;
import com.example.Cat.s.Blog.entity.roles.RoleType;
import com.example.Cat.s.Blog.entity.users.User;
import com.example.Cat.s.Blog.services.user.impl.StandardUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StandardUserService userService;

    private User user;
    private final String usernameSaved = "username000";
    private final String usernameNonSaved = "username";
    private final Long idSaved = 1L;
    private final Long idNonSaved = 2L;
    private final Role role = new Role(RoleType.USER);

//    @BeforeEach
//    public void setup() {
//        if (roleRepository.findByName(RoleType.USER) == null) {
//            roleRepository.saveAndFlush(role);
//        }
//        user = new User(usernameSaved, role);
//        userRepository.saveAndFlush(user);
//    }
//
//
//    @AfterEach
//    public void cleanUp() {
//        userRepository.deleteAll();
//    }

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
        userService.update(idSaved, usernameNonSaved, role);
        Assertions.assertEquals(userRepository.count(), 1);
    }

    @Test
    public void updateTestWhenUpdateUserThenInformationChange() {
        Assertions.assertEquals(userRepository.findById(idSaved).get().getUsername(), usernameSaved);
        userService.update(idSaved, usernameNonSaved, role);
        Assertions.assertEquals(userRepository.findById(idSaved).get().getUsername(), usernameNonSaved);
    }

    @Test
    public void updateTestWhenUpdateUserWithIdThatInRepositoryThenTrue() {
        Assertions.assertTrue(userService.update(idSaved, usernameNonSaved, role));
    }

    @Test
    public void updateTestWhenUpdateUserWithIdThatNotInRepositoryThenFalse() {
        Assertions.assertFalse(userService.update(idNonSaved, usernameNonSaved, role));
    }


    @Test
    public void deleteTestWhenDeleteUserNumberOfRecordsInRepositoryDecreasing() {
        Assertions.assertEquals(userRepository.count(), 1);
        userService.delete(idSaved);
        Assertions.assertEquals(userRepository.count(), 0);
    }

    @Test
    public void deleteTestWhenUpdateUserWithIdThatInRepositoryThenTrue() {
        Assertions.assertTrue(userService.update(0L, usernameNonSaved, role));
    }

    @Test
    public void deleteTestWhenUpdateUserWithIdThatNotInRepositoryThenFalse() {
        Assertions.assertFalse(userService.update(idNonSaved, usernameNonSaved, role));
    }

}
