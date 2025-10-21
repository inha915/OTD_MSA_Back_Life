package com.otd.otd_msa_back_life.water_intake.repository;

import com.otd.otd_msa_back_life.water_intake.entity.DailyWaterIntake;

import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakeGetRes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface DailyWaterIntakeRepository extends JpaRepository<DailyWaterIntake, Long> {
    DailyWaterIntake findByUserIdAndIntakeDate(Long userId, LocalDate date);

    void deleteAllByUserId(Long userId);
}
