package com.otd.otd_msa_back_life.admin.model.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExerciseNameRecordCountRes {
    private String exerciseName;
    private Long recordCount;
}
