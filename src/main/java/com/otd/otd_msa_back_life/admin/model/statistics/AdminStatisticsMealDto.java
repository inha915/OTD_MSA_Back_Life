package com.otd.otd_msa_back_life.admin.model.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AdminStatisticsMealDto {
    private List<MealRecordCountRes> mealRecordCount;
    private MealMacroAverageRes mealMacroAverage;
}
