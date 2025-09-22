package com.otd.otd_msa_back_life.otd_meal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@Setter
@ToString
public class GetMealListReq {

    private int memberNoLogin;
    private String mealBrLuDi;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate  mealDay;

}
