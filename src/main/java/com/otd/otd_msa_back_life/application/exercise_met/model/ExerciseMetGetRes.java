package com.otd.otd_msa_back_life.application.exercise_met.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExerciseMetGetRes {
    private Long exerciseId;
    private String exerciseName;
    private double exerciseMet;
}
