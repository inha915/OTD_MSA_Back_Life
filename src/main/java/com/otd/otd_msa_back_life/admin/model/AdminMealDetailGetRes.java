package com.otd.otd_msa_back_life.admin.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdminMealDetailGetRes {
    private String foodName;
    private LocalDate mealDay;
    private String mealTime;
    private Float carbohydrate;
    private Float natrium;
    private Float protein;
    private Float fat;
    private Float sugar;
    private String flag;
    private int kcal;
    private int foodAmount;
}
