package com.otd.otd_msa_back_life.admin.model.dashboard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AdminDashBoardMealDto {
    private int totalRecordCount;
    private int weeklyRecordUserCount;
    private Double calorieAverage;
}
