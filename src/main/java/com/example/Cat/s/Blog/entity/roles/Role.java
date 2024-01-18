package com.example.Cat.s.Blog.entity.roles;

import com.sun.istack.NotNull;
import lombok.*;

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
//    @Column(unique = true)
//    @NotNull
    private RoleType name;

    public Role(RoleType role) {
        this.name = role;
    }

}
