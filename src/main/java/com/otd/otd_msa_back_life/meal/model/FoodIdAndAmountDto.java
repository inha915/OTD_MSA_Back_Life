package com.otd.otd_msa_back_life.meal.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@Builder
public class FoodIdAndAmountDto {
    private Integer foodDbId;
    private Integer  foodAmount;

}
