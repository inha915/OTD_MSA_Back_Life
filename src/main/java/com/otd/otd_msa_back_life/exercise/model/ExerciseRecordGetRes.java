package com.otd.otd_msa_back_life.exercise.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class ExerciseRecordGetRes {
    private int exerciseRecordId;
    private int exerciseId;
    private int exerciseDuration;
    private int activityKcal;
    private int effortLevel;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
