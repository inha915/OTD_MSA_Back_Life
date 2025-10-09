package com.otd.otd_msa_back_life.meal.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.otd.otd_msa_back_life.meal.entity.MealFoodDb;
import com.otd.otd_msa_back_life.meal.entity.MealFoodMakeDb;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@ToString
public class MealMainListRes {

    private int foodAmount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate mealDay;
    private String mealTime;

    private Long foodId;
    private Long userFoodId;

    private List<MealFoodDb> mealFood;
    private List<MealFoodMakeDb> mealFoodMake;

}
