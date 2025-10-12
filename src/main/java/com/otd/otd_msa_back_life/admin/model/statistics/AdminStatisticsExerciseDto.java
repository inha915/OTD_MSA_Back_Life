package com.otd.otd_msa_back_life.admin.model.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AdminStatisticsExerciseDto {
    private List<ExerciseRecordCountRes> exerciseRecordCountResList;
    private List<ExerciseNameRecordCountRes> exerciseNameRecordCountResList;
    private List<ExerciseDistributionCountRes> exerciseDistributionCountResList;
}
