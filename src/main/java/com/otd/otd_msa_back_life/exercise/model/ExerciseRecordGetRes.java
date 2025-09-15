package com.otd.otd_msa_back_life.exercise.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class ExerciseRecordGetRes {
    private Integer exerciseRecordId;
    private Integer exerciseId;
    private Integer activityKcal;
    private Integer effortLevel;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Double distance;
}
