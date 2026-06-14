package com.fitness.fitness.service.impl;

import com.fitness.fitness.entity.Workout;
import com.fitness.fitness.repository.WorkoutRepository;
import com.fitness.fitness.service.WorkoutService;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public Workout create(Workout workout) {
        workout.setCreatedAt(Instant.now());
        if (workout.getIsCompleted() == null) workout.setIsCompleted(false);
        return workoutRepository.save(workout);
    }

    @Override
    public List<Workout> findByClientId(Long clientId) {
        return workoutRepository.findByClientId(clientId);
    }

    @Override
    public Optional<Workout> findById(Long id) {
        return workoutRepository.findById(id);
    }

    @Override
    public Workout update(Long id, Workout workout) {
        return workoutRepository.findById(id).map(existing -> {
            existing.setExerciseName(workout.getExerciseName());
            existing.setTargetReps(workout.getTargetReps());
            existing.setTargetSets(workout.getTargetSets());
            existing.setIsCompleted(workout.getIsCompleted());
            existing.setClientComment(workout.getClientComment());
            return workoutRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Workout not found"));
    }

    @Override
    public void delete(Long id) {
        workoutRepository.deleteById(id);
    }
}
