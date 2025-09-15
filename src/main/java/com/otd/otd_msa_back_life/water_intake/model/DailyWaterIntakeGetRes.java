package com.otd.otd_msa_back_life.water_intake.model;

import com.otd.otd_msa_back_life.water_intake.entity.DailyWaterIntake;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Getter
@Slf4j
@AllArgsConstructor
public class DailyWaterIntakeGetRes {
    private LocalDate intakeDate;
    private Double amountLiter;

    public DailyWaterIntakeGetRes(DailyWaterIntake dailyWaterIntake) {
        this.intakeDate = dailyWaterIntake.getIntakeDate();
        this.amountLiter = dailyWaterIntake.getAmountLiter();
    }
}
