package com.otd.otd_msa_back_life.meal.repository;

import com.otd.otd_msa_back_life.meal.entity.MealRecord;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MealRecordRepository extends JpaRepository<MealRecord, Long> {
    @Modifying
    @Transactional
    int deleteByUserIdAndMealRecordIdsMealDayAndMealRecordIdsMealTime(
            Long userId, LocalDate mealDay, String mealTime
    );

    @Query("""
        select mr
        from MealRecord mr
        left join fetch mr.foodDb
        left join fetch mr.userFood
        where mr.userId = :userId
          and mr.mealRecordIds.mealDay = :mealDay
    """)
    List<MealRecord> findForMain(Long userId, LocalDate mealDay);
}
