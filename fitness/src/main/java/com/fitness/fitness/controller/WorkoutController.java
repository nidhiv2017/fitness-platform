package com.fitness.fitness.controller;

import com.fitness.fitness.entity.Workout;
import com.fitness.fitness.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<Workout> create(@RequestBody Workout workout) {
        Workout saved = workoutService.create(workout);
        return ResponseEntity.created(URI.create("/api/workouts/" + saved.getId())).body(saved);
    }

    @GetMapping("/client/{clientId}")
    public List<Workout> getByClient(@PathVariable Long clientId) {
        return workoutService.findByClientId(clientId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workout> update(@PathVariable Long id, @RequestBody Workout workout) {
        return ResponseEntity.ok(workoutService.update(id, workout));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        workoutService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
