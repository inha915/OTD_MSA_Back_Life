package com.otd.otd_msa_back_life.otd_meal.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class GetFoodInfoAllRes {
    private int calorie;
    private float protein;
    private float fat;
    private float carbohydrate;
    private float sugar;
    private float natrium;
}
