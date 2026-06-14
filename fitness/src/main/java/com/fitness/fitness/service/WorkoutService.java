package com.fitness.fitness.service;

import com.fitness.fitness.entity.Workout;
import java.util.List;
import java.util.Optional;

public interface WorkoutService {
    Workout create(Workout workout);
    List<Workout> findByClientId(Long clientId);
    Optional<Workout> findById(Long id);
    Workout update(Long id, Workout workout);
    void delete(Long id);
}
