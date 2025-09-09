package com.otd.otd_msa_back_life.meal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class MealNutritionIds implements Serializable {

    @Column(nullable = false)
    private String mealTime;

    @Column(nullable = false)
    private LocalDateTime mealDate;

}
