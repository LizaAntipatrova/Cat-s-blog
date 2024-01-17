package com.example.Cat.s.Blog.test;

import com.example.Cat.s.Blog.entity.posts.Blogpost;
import com.example.Cat.s.Blog.entity.repositories.BlogpostRepository;
import com.example.Cat.s.Blog.entity.repositories.RoleRepository;
import com.example.Cat.s.Blog.entity.repositories.UserRepository;
import com.example.Cat.s.Blog.entity.roles.Role;
import com.example.Cat.s.Blog.entity.roles.RoleType;
import com.example.Cat.s.Blog.entity.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.sql.Date;


public class TestDataFiller {
    @Autowired
    private RoleRepository repositoryRole;
    @Autowired
    private BlogpostRepository repositoryPost;
    @Autowired
    private UserRepository repositoryUser;


    @EventListener(ApplicationReadyEvent.class)
    public void fillTestData() {
        Role roleUsr = new Role(RoleType.USER);
        Role roleAdm = new Role(RoleType.ADMIN);

        repositoryRole.save(roleAdm);
        repositoryRole.save(roleUsr);


        for (int i = 1; i < 3; i++) {
            User usr = new User("usr " + i, roleUsr);
            User adm = new User("usr 1" + i, roleAdm);
            repositoryUser.save(usr);
            repositoryUser.save(adm);
            Blogpost post = new Blogpost("content " + i, "title " + i, usr, new Date(System.currentTimeMillis()));
            repositoryPost.save(post);
        }

    }
}
