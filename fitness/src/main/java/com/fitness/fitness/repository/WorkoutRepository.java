package com.fitness.fitness.repository;

import com.fitness.fitness.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByClientId(Long clientId);
}
