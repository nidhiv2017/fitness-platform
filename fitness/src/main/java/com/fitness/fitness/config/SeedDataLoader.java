package com.fitness.fitness.config;

import com.fitness.fitness.entity.User;
import com.fitness.fitness.entity.TrainerClientRoster;
import com.fitness.fitness.entity.Workout;
import com.fitness.fitness.repository.UserRepository;
import com.fitness.fitness.repository.TrainerClientRosterRepository;
import com.fitness.fitness.repository.WorkoutRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;

@Configuration
public class SeedDataLoader {

    @Bean
    CommandLineRunner init(UserRepository userRepo, TrainerClientRosterRepository rosterRepo, WorkoutRepository workoutRepo) {
        return args -> {
            if (userRepo.count() > 0) return;

            User trainer = User.builder()
                    .name("Primary Trainer")
                    .email("trainer@example.com")
                    .password("trainer123")
                    .role("trainer")
                    .age(30)
                    .gender("other")
                    .createdAt(Instant.now())
                    .build();
            trainer = userRepo.save(trainer);

            User client = User.builder()
                    .name("Client One")
                    .email("client@example.com")
                    .password("client123")
                    .role("client")
                    .age(25)
                    .gender("female")
                    .createdAt(Instant.now())
                    .build();
            client = userRepo.save(client);

            // additional demo clients
            List<User> demo = List.of(
                    User.builder().name("Sarah").email("sarah@example.com").password("pass").role("client").age(28).gender("female").createdAt(Instant.now()).build(),
                    User.builder().name("John").email("john@example.com").password("pass").role("client").age(32).gender("male").createdAt(Instant.now()).build(),
                    User.builder().name("Emma").email("emma@example.com").password("pass").role("client").age(22).gender("female").createdAt(Instant.now()).build(),
                    User.builder().name("David").email("david@example.com").password("pass").role("client").age(40).gender("male").createdAt(Instant.now()).build(),
                    User.builder().name("Olivia").email("olivia@example.com").password("pass").role("client").age(27).gender("female").createdAt(Instant.now()).build()
            );
            List<User> saved = userRepo.saveAll(demo);

            // second trainer
            User trainer2 = User.builder().name("Secondary Trainer").email("trainer2@example.com").password("trainer123").role("trainer").age(35).gender("male").createdAt(Instant.now()).build();
            trainer2 = userRepo.save(trainer2);

            // assign one demo client (Sarah) to trainer2
            User sarah = userRepo.findByEmail("sarah@example.com").orElse(null);
            if (sarah != null) {
                rosterRepo.save(TrainerClientRoster.builder().trainerId(trainer2.getId()).clientId(sarah.getId()).createdAt(Instant.now()).build());
            }

            // assign primary trainer to the main demo client
            rosterRepo.save(TrainerClientRoster.builder().trainerId(trainer.getId()).clientId(client.getId()).createdAt(Instant.now()).build());

            // create some workouts
            Workout w1 = Workout.builder().clientId(client.getId()).trainerId(trainer.getId()).exerciseName("Push Ups").targetSets(3).targetReps(12).isCompleted(false).createdAt(Instant.now()).build();
            Workout w2 = Workout.builder().clientId(client.getId()).trainerId(trainer.getId()).exerciseName("Squats").targetSets(4).targetReps(15).isCompleted(false).createdAt(Instant.now()).build();
            workoutRepo.save(w1);
            workoutRepo.save(w2);
        };
    }
}
