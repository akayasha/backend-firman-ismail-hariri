package com.backend.backend_firman_ismail_hariri.service;

import com.backend.backend_firman_ismail_hariri.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findById(String id);
    User findByUsername(String username);
    List<User> findAll();
    User create(User user);
}