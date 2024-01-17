package com.example.Cat.s.Blog.services.user.impl;

import com.example.Cat.s.Blog.entity.repositories.RoleRepository;
import com.example.Cat.s.Blog.entity.repositories.UserRepository;
import com.example.Cat.s.Blog.entity.roles.Role;
import com.example.Cat.s.Blog.entity.roles.RoleType;
import com.example.Cat.s.Blog.entity.users.User;
import com.example.Cat.s.Blog.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StandardUserService implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean add(String username) {
        if (userRepository.findByUsername(username) != null) {
            User addedUser = new User(
                    username,
                    roleRepository.findByName(RoleType.USER));
            userRepository.save(addedUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, String username, Role userRole) {
        Optional foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            User updatedUser = (User) foundUser.get();
            updatedUser.setUsername(username);
            updatedUser.setUserRole(userRole);
            userRepository.save(updatedUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Optional foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
