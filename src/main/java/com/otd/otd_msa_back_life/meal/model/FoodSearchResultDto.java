package com.otd.otd_msa_back_life.meal.model;


import jakarta.persistence.Column;
import lombok.*;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
public class FoodSearchResultDto {

    private Long foodDbId;      // MealFoodDb.id or MealFoodMakeDb.userFoodId
    private String foodName;
    private String flag;

    private Integer kcal;
    private float protein;
    private float carbohydrate;
    private float fat;
    private float sugar;
    private float natrium;

    private int foodCapacity;
}
