package com.otd.otd_msa_back_life.meal.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class InputMealRecordDetailDto {

    private int kcal;
    private float protein;
    private float fat;
    private float carbohydrate;
    private float sugar;
    private float natrium;

    private Long foodDbId;
    private String foodName;
    private String flag;
    private int amount;
}
