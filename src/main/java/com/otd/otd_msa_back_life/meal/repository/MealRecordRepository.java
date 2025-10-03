package com.otd.otd_msa_back_life.meal.repository;

import com.otd.otd_msa_back_life.meal.entity.MealRecord;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDate;

public interface MealRecordRepository extends JpaRepository<MealRecord, Long> {
    @Modifying
    @Transactional
    int deleteByUserIdAndMealRecordIdsMealDayAndMealRecordIdsMealTime(
            Long userId, LocalDate mealDay, String mealTime
    );

}
