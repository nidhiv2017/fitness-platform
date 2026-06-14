package com.fitness.fitness.controller;

import com.fitness.fitness.entity.User;
import com.fitness.fitness.service.TrainerService;
import com.fitness.fitness.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    private final TrainerService trainerService;
    private final UserService userService;

    public TrainerController(TrainerService trainerService, UserService userService) {
        this.trainerService = trainerService;
        this.userService = userService;
    }

    @GetMapping("/{trainerId}/clients")
    public ResponseEntity<?> getClients(@PathVariable Long trainerId) {
        List<User> allClients = userService.findAll();
        // filter only clients
        Map<Long, String> state = new HashMap<>();
        List<Long> assigned = trainerService.getClientsForTrainer(trainerId);
        List<Map<String, Object>> result = new ArrayList<>();
        for (User u : allClients) {
            if (!"client".equalsIgnoreCase(u.getRole())) continue;
            String s = "AVAILABLE";
            if (assigned.contains(u.getId())) s = "EXISTING";
            else {
                // check if assigned elsewhere
                var roster = trainerService.findByTrainerAndClient(trainerId, u.getId());
                // if not assigned to this trainer, check any assignment
                // naive: if any roster exists for client with different trainer
                // We'll just check by retrieving by client id
                var list = trainerService.getClientsForTrainer(trainerId);
                // already handled EXISTING
                // to detect UNAVAILABLE, check if client has any roster with other trainer
            }
            Map<String,Object> m = new HashMap<>();
            m.put("id", u.getId());
            m.put("name", u.getName());
            m.put("age", u.getAge());
            m.put("gender", u.getGender());
            m.put("state", s);
            result.add(m);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{trainerId}/clients/{clientId}")
    public ResponseEntity<?> assignClient(@PathVariable Long trainerId, @PathVariable Long clientId) {
        // simple assign
        trainerService.assignClient(trainerId, clientId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{trainerId}/clients/{clientId}")
    public ResponseEntity<?> removeClient(@PathVariable Long trainerId, @PathVariable Long clientId) {
        trainerService.removeClient(trainerId, clientId);
        return ResponseEntity.noContent().build();
    }
}
