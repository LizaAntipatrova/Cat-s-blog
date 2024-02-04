package com.example.Cat.s.Blog.db.entity.roles;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)        //храним енам как строку
    private RoleType name;

    public Role(RoleType role) {
        this.name = role;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }
}
