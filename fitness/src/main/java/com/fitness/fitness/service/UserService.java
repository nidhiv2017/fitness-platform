package com.fitness.fitness.service;

import com.fitness.fitness.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User create(User user);
    User update(Long id, User user);
    void delete(Long id);
    Optional<User> findByEmail(String email);
}
