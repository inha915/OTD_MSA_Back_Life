package com.otd.otd_msa_back_life.water_intake.repository;

import com.otd.otd_msa_back_life.water_intake.entity.DailyWaterIntake;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DailyWaterIntakeRepository extends JpaRepository<DailyWaterIntake, Long> {
}
