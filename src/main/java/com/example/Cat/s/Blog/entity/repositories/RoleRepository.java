package com.example.Cat.s.Blog.entity.repositories;

import com.example.Cat.s.Blog.entity.roles.Role;
import com.example.Cat.s.Blog.entity.roles.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleType nameRole);
}
