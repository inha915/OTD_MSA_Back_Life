package com.otd.otd_msa_back_life.meal.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
public class InputMealRecordReq {

    private String mealTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate mealDay;

    private List<InputMealRecordDetailDto> foods;

}
