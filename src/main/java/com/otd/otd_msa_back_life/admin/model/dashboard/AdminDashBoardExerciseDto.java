package com.otd.otd_msa_back_life.admin.model.dashboard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminDashBoardExerciseDto {
    private int totalRecordCount;
    private int weeklyRecordUserCount;
    private Double dailyExerciseAverage;
}
