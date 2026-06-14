package com.fitness.fitness.repository;

import com.fitness.fitness.entity.TrainerClientRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainerClientRosterRepository extends JpaRepository<TrainerClientRoster, Long> {
    List<TrainerClientRoster> findByTrainerId(Long trainerId);
    List<TrainerClientRoster> findByClientId(Long clientId);
    TrainerClientRoster findByTrainerIdAndClientId(Long trainerId, Long clientId);
}
