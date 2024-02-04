package com.example.Cat.s.Blog;

import com.example.Cat.s.Blog.db.entity.roles.Role;
import com.example.Cat.s.Blog.db.entity.roles.RoleType;
import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.db.repositories.RoleRepository;
import com.example.Cat.s.Blog.db.repositories.UserRepository;
import com.example.Cat.s.Blog.services.exceptions.ExistingUsernameException;
import com.example.Cat.s.Blog.services.exceptions.NonExistingUserException;
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
    private final String password = "password";

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

        user = new User(usernameSaved, password, role);
        userRepository.saveAndFlush(user);
    }

    @AfterEach
    public void cleanUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();

    }

    @Test
    public void showByIdTestWhenUserExistedThenGetUser() {
        Assertions.assertEquals(userRepository.findById(user.getId()).get(), userService.showById(user.getId()));
    }

    @Test
    public void showByIdTestWhenUserNonExistedThenNonExistingUserException() {
        Assertions.assertThrows(NonExistingUserException.class,
                () -> userService.showById(Long.MAX_VALUE - 1));
    }

    @Test
    public void addTestWhenUserAddedThenUserWithThatUsernameAppearsInRepository() {
        userService.add(usernameNonSaved, password);
        Assertions.assertNotNull(userRepository.findByUsername(usernameNonSaved));
    }

    @Test
    public void addTestWhenUserWithNameThatInRepositoryAddedThenExistingUsernameException() {
        Assertions.assertThrows(ExistingUsernameException.class, () -> userService.add(usernameSaved, password));

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
    public void updateTestWhenUpdateUserWithIdThatNotInRepositoryThenNonExistingUserException() {
        Assertions.assertThrows(NonExistingUserException.class, () -> userService.update(Long.MAX_VALUE - 1, usernameNonSaved, role));
    }


    @Test
    public void deleteTestWhenDeleteUserNumberOfRecordsInRepositoryDecreasing() {
        Long idSaved = user.getId();
        Assertions.assertEquals(userRepository.count(), 1);
        userService.delete(idSaved);
        Assertions.assertEquals(userRepository.count(), 0);
    }

    @Test
    public void deleteTestWhenDeleteUserWithIdThatNotInRepositoryThenNonExistingUserException() {
        Assertions.assertThrows(NonExistingUserException.class, () -> userService.delete(Long.MAX_VALUE - 1));
    }

}
