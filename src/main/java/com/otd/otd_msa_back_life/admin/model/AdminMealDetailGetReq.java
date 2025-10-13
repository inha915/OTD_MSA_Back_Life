package com.otd.otd_msa_back_life.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminMealDetailGetReq {
    private String mealDay;
    private String mealTime;
    private Long userId;
}
