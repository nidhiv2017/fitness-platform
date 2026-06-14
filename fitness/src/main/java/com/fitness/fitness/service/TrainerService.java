package com.fitness.fitness.service;

import com.fitness.fitness.entity.TrainerClientRoster;
import java.util.List;

public interface TrainerService {
    List<Long> getClientsForTrainer(Long trainerId);
    TrainerClientRoster assignClient(Long trainerId, Long clientId);
    void removeClient(Long trainerId, Long clientId);
    TrainerClientRoster findByTrainerAndClient(Long trainerId, Long clientId);
}
