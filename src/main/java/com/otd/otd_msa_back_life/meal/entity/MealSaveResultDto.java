package com.otd.otd_msa_back_life.meal.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MealSaveResultDto {
    private int savedCount;
    private List<Long> mealRecordIds;
    private List<Long> newUserFoodIds;
}

//아래 방식도 있음
//public record MealSaveResultDto(
//        int savedCount,
//        List<Long> mealRecordIds,
//        List<Long> newUserFoodIds
//) {}