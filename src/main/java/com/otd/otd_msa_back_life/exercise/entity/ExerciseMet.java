package com.otd.otd_msa_back_life.exercise.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ExerciseMet {
    @Id
    private Long exerciseId;

    @Column(nullable = false, length = 30)
    private String exerciseName;

    @Column(nullable = false)
    private double exerciseMet;
}