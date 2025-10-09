package com.otd.otd_msa_back_life.meal.repository;

import com.otd.otd_msa_back_life.meal.entity.MealRecordDetail;
import com.otd.otd_msa_back_life.meal.model.GetSummaryTotalDto;
import org.springframework.data.repository.query.Param; // JPA용
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface MealRecordDetailRepository extends JpaRepository<MealRecordDetail, Long> {
    @Modifying
    @Transactional
    int deleteByUserIdAndMealRecordIdsMealDayAndMealRecordIdsMealTime(
            Long userId, LocalDate mealDay, String mealTime
    );

    @Query("SELECT COALESCE(SUM(m.totalProtein), 0) " +
            "FROM MealRecordDetail m " +
            "WHERE m.userId = :userId " +
            "AND m.mealRecordIds.mealDay = :mealDay")
    Double findTotalProteinByUserAndDay(@Param("userId") Long userId,
                                        @Param("mealDay") LocalDate mealDay);

    @Query(
            "SELECT new com.otd.otd_msa_back_life.meal.model.GetSummaryTotalDto(" +
                    "  COALESCE(SUM(m.totalCarbohydrate * 1.0), 0.0), " +
                    "  COALESCE(SUM(m.totalProtein      * 1.0), 0.0), " +
                    "  COALESCE(SUM(m.totalFat          * 1.0), 0.0), " +
                    "  COALESCE(SUM(m.totalSugar        * 1.0), 0.0), " +
                    "  COALESCE(SUM(m.totalNatrium      * 1.0), 0.0), " +
                    "  COALESCE(SUM(m.totalKcal         * 1.0), 0.0)  " +
                    ") " +
                    "FROM MealRecordDetail m " +
                    "WHERE m.userId = :userId " +
                    "AND m.mealRecordIds.mealDay >= :startDay " +
                    "AND m.mealRecordIds.mealDay <  :endDay"
    )
    GetSummaryTotalDto sumBetween(@Param("userId") Long userId,
                                  @Param("startDay") LocalDate startDay,
                                  @Param("endDay") LocalDate endDay);
}