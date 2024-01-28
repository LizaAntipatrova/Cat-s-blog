package com.example.Cat.s.Blog.db.entity.users;

import com.example.Cat.s.Blog.db.entity.roles.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @ManyToOne(fetch = FetchType.EAGER)
    private Role userRole;

    public User(String username, Role userRole) {
        this.username = username;
        this.userRole = userRole;
    }


}
