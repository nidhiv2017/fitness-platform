package com.fitness.fitness.controller;

import com.fitness.fitness.entity.User;
import com.fitness.fitness.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<User> getUsers() {
        return repo.findAll();
    }
}