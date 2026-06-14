package com.fitness.fitness.repository;

import com.fitness.fitness.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {
}