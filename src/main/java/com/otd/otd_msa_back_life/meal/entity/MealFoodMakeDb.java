package com.otd.otd_msa_back_life.meal.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MealFoodMakeDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userFoodId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String foodName;

    @Column(nullable = false, length = 10)
    private String flag;

    @Positive
    @Column(nullable = false)
    private Integer kcal;

    @PositiveOrZero
    @Column(nullable = false)
    private float protein;

    @PositiveOrZero
    @Column(nullable = false)
    private float carbohydrate;

    @PositiveOrZero
    @Column(nullable = false)
    private float fat;

    @PositiveOrZero
    @Column(nullable = false)
    private float sugar;

    @PositiveOrZero
    @Column(nullable = false)
    private float natrium;

}
