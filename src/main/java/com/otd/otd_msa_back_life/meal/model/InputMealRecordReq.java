package com.otd.otd_msa_back_life.meal.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class InputMealRecordReq {

    private List<Integer> foodDbId;
    private int  memberNoLogin;
    private List<Integer> amount;
    private String mealBrLuDi;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate mealDay;

    private List<InputMealRecordDetailDto> detailDto;

}
