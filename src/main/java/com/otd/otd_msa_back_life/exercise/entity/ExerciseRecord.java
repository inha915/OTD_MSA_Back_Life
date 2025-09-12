package com.otd.otd_msa_back_life.exercise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ExerciseRecord  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long exerciseRecordId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "exercise_id")
    private ExerciseCatalog exercise;

    private Long memberId;

    @Column(nullable = false)
    private Integer effortLevel;

    @Column(nullable = false)
    private Integer exerciseDuration;

    @Column(nullable = false)
    private Integer activityKcal;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

}