package com.otd.otd_msa_back_life.meal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MealRecordDetail {
    @EmbeddedId
    MealRecordIds mealRecordIds;

    @Column(nullable = false)
    private Long memberId;

    @Positive
    private Integer totalKcal;

    @Positive
    private Double totalProtein;

    @Positive
    private Double totalCarbohydrate;

    @Positive
    private Double totalFat;

    @Positive
    private Double totalSugar;

    @Positive
    private Double totalNatrium;


}
