package com.otd.otd_msa_back_life.otd_meal.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class InpuMealDetailDto {
    private int  memberNoLogin;
    private String mealBrLuDi;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate mealDay;
    private int totalCalorie;
    private float totalProtein;
    private float totalFat;
    private float totalCarbohydrate;
    private float totalSugar;
    private float totalNatrium;

}
