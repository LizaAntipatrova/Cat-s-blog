package com.example.Cat.s.Blog.test;

import com.example.Cat.s.Blog.db.entity.posts.Blogpost;
import com.example.Cat.s.Blog.db.entity.roles.Role;
import com.example.Cat.s.Blog.db.entity.roles.RoleType;
import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.db.repositories.BlogpostRepository;
import com.example.Cat.s.Blog.db.repositories.RoleRepository;
import com.example.Cat.s.Blog.db.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@RequiredArgsConstructor
@Profile("!test")       // spring_profiles_active=test в edit configuration стратегия игнора: https://www.baeldung.com/spring-profiles
public class TestDataFiller {

    private final RoleRepository repositoryRole;

    private final BlogpostRepository repositoryPost;

    private final UserRepository repositoryUser;


    @EventListener(ApplicationReadyEvent.class)
    public void fillTestData() {
        Role roleUsr = new Role(RoleType.USER);
        Role roleAdm = new Role(RoleType.ADMIN);

        repositoryRole.saveAndFlush(roleAdm);
        repositoryRole.saveAndFlush(roleUsr);


        for (int i = 1; i < 3; i++) {
            User usr = new User("usr " + i, roleUsr);
            User adm = new User("usr 1" + i, roleAdm);
            repositoryUser.saveAndFlush(usr);
            repositoryUser.saveAndFlush(adm);
            Blogpost post = new Blogpost("content " + i, "title " + i, usr, new Date(System.currentTimeMillis()));
            repositoryPost.saveAndFlush(post);
        }

    }
}
