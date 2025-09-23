package com.otd.otd_msa_back_life.meal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FindFoodNameReq {
    private String foodName;
    private String foodCategory;
}
