package com.otd.otd_msa_back_life.meal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@ToString
@Builder
@Setter
@Getter
public class GetMyDayDateDto {


    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at", updatable = false)
    private LocalDate selectDay;

    private  Double basalMetabolicRate;  // 기초대사량

    private Integer activityKcal;

}
