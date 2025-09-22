package com.otd.otd_msa_back_life.otd_meal.model;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class FindFoodNameRes {
    private int foodDbId;
    private String foodName;
    private int calorie;
}
