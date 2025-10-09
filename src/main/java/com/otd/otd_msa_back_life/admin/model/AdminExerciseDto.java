package com.otd.otd_msa_back_life.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AdminExerciseDto {
    private Long exerciseRecordId;
    private Long userId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Integer duration;
    private Double distance;
    private Integer reps;
    private Integer activityKcal;
    private String exerciseName;
}