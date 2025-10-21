package com.otd.otd_msa_back_life.exercise.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExerciseCatalogGetRes {
    private Long exerciseId;
    private String exerciseName;
    private Double exerciseMet;
    private Boolean hasDistance;
    private Boolean hasReps;
}
