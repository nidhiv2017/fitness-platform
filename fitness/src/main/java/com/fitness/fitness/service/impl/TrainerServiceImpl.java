package com.fitness.fitness.service.impl;

import com.fitness.fitness.entity.TrainerClientRoster;
import com.fitness.fitness.repository.TrainerClientRosterRepository;
import com.fitness.fitness.service.TrainerService;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerClientRosterRepository rosterRepository;

    public TrainerServiceImpl(TrainerClientRosterRepository rosterRepository) {
        this.rosterRepository = rosterRepository;
    }

    @Override
    public List<Long> getClientsForTrainer(Long trainerId) {
        return rosterRepository.findByTrainerId(trainerId).stream()
                .map(TrainerClientRoster::getClientId)
                .collect(Collectors.toList());
    }

    @Override
    public TrainerClientRoster assignClient(Long trainerId, Long clientId) {
        TrainerClientRoster existing = rosterRepository.findByTrainerIdAndClientId(trainerId, clientId);
        if (existing != null) return existing;
        TrainerClientRoster r = TrainerClientRoster.builder()
                .trainerId(trainerId)
                .clientId(clientId)
                .createdAt(Instant.now())
                .build();
        return rosterRepository.save(r);
    }

    @Override
    public void removeClient(Long trainerId, Long clientId) {
        TrainerClientRoster existing = rosterRepository.findByTrainerIdAndClientId(trainerId, clientId);
        if (existing != null) rosterRepository.delete(existing);
    }

    @Override
    public TrainerClientRoster findByTrainerAndClient(Long trainerId, Long clientId) {
        return rosterRepository.findByTrainerIdAndClientId(trainerId, clientId);
    }
}
