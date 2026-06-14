package com.fitness.fitness.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "trainer_id")
    private Long trainerId;

    private String exerciseName;

    private Integer targetSets;

    private Integer targetReps;

    private Boolean isCompleted;

    private String clientComment;

    private Instant createdAt;
}
