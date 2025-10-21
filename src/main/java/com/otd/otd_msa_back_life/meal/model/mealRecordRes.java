package com.otd.otd_msa_back_life.meal.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class mealRecordRes {

    private Integer foodAmount;
    private Long userId;
    private Long foodId;
    private Long userFoodId;
    private String mealDay;
    private String mealTime;
}
