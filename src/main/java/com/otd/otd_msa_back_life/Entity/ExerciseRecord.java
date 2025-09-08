package com.otd.otd_msa_back_life.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ExerciseRecord{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long exerciseRecordId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "exercise_id")
    private ExerciseMet exercise;

    @Column(nullable = false)
    private Integer effortLevel;

    @Column(nullable = false)
    private Integer exerciseDuration;

    @Column(nullable = false)
    private Integer activityKcal;

    @Column(nullable = false)
    private LocalDateTime startDatetime;

    @Column(nullable = false)
    private LocalDateTime endDatetime;

}