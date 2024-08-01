package com.backend.backend_firman_ismail_hariri.service.impl;

import com.backend.backend_firman_ismail_hariri.model.entity.User;
import com.backend.backend_firman_ismail_hariri.repository.UserRepository;
import com.backend.backend_firman_ismail_hariri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(String id) {
        return userRepository.findByUsername(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}
