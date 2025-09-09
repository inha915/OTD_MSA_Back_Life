package com.otd.otd_msa_back_life.meal.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MealRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long mealId;

    @Positive
    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Long memberId;


   private MealNutritionIds mealNutritionIds;




}
