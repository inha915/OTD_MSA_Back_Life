package com.otd.otd_msa_back_life.water_intake.model;

import com.otd.otd_msa_back_life.water_intake.entity.DailyWaterIntake;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Getter
@Slf4j
@AllArgsConstructor
@Setter
public class DailyWaterIntakeGetRes {
    private Long dailyWaterIntakeId;
    private LocalDate intakeDate;
    private Double amountLiter;

    public DailyWaterIntakeGetRes(DailyWaterIntake dailyWaterIntake) {
       this.dailyWaterIntakeId = dailyWaterIntake.getDailyWaterIntakeId();
        this.intakeDate = dailyWaterIntake.getIntakeDate();
        this.amountLiter = dailyWaterIntake.getAmountLiter();
    }
}
