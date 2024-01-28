package com.example.Cat.s.Blog.db.entity.roles;

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
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)        //храним енам как строку
    private RoleType name;

    public Role(RoleType role) {
        this.name = role;
    }

}
