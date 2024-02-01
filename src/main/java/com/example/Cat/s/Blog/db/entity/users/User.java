package com.example.Cat.s.Blog.db.entity.users;

import com.example.Cat.s.Blog.db.entity.posts.Blogpost;
import com.example.Cat.s.Blog.db.entity.roles.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;
    @Column(unique = true)
    private String username;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private Role userRole;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    private List<Blogpost> posts;

    public User(String username, Role userRole) {
        this.username = username;
        this.userRole = userRole;
    }


}
