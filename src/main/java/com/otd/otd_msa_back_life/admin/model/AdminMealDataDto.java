package com.otd.otd_msa_back_life.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AdminMealDataDto {
    private Long mealId;
    private String foodName;
    private String mealTime;
    private LocalDate mealDay;
    private Integer foodAmount;
    private Float totalCarbohydrate;
    private Float totalFat;
    private Integer totalKcal;
    private Float totalNatrium;
    private Float totalProtein;
    private Float totalSugar;
}
