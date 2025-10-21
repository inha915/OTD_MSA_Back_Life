package com.otd.otd_msa_back_life.water_intake.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Getter
@Slf4j
public class DailyWaterIntakePutReq {
    private Long dailyWaterIntakeId;
    private Double amountLiter;
    private LocalDate intakeDate;
}
