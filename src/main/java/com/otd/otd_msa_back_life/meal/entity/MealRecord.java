package com.otd.otd_msa_back_life.meal.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

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
    private Integer foodAmount;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "food_id")
    private MealFoodDb foodDb; // food db의  foodid와 연결

    @Embedded
    private MealRecordIds mealRecordIds;
}
