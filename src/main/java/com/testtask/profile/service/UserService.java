package com.testtask.profile.service;

import com.testtask.profile.exception.RestException;
import com.testtask.profile.model.User;
import com.testtask.profile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        if (user.isNew()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public User findByName(String name) {
        return userRepository.findByNameIgnoreCase(name);
    }

    public User findByLoginAndPassword(String name, String password) {
        User user = findByName(name);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        throw new RestException.NotFoundException();
    }
}
