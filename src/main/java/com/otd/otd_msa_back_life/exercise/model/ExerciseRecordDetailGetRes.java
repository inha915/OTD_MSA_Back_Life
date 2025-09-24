package com.otd.otd_msa_back_life.exercise.model;


import com.otd.otd_msa_back_life.exercise.entity.ExerciseRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class ExerciseRecordDetailGetRes {
    private Long exerciseRecordId;
    private Long exerciseId;
    private String exerciseName;
    private Integer activityKcal;
    private Integer effortLevel;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Double distance;
    private Integer reps;


}
