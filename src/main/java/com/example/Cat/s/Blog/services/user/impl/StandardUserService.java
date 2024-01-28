package com.example.Cat.s.Blog.services.user.impl;

import com.example.Cat.s.Blog.db.repositories.RoleRepository;
import com.example.Cat.s.Blog.db.repositories.UserRepository;
import com.example.Cat.s.Blog.db.entity.roles.Role;
import com.example.Cat.s.Blog.db.entity.roles.RoleType;
import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandardUserService implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public Optional<User> showById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public List<User> showAll(){
        return userRepository.findAll();
    }

    @Override
    public boolean add(String username) {

        if (userRepository.findByUsername(username) == null) {
            User addedUser = new User(
                    username,
                    roleRepository.findByName(RoleType.USER));
            userRepository.saveAndFlush(addedUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, String username, Role userRole) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            User updatedUser = foundUser.get();
            updatedUser.setUsername(username);
            updatedUser.setUserRole(userRole);
            userRepository.saveAndFlush(updatedUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
