package com.otd.otd_msa_back_life.meal.repository;

import com.otd.otd_msa_back_life.meal.entity.MealRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRecordRepository extends JpaRepository<MealRecord, Long> {
}
