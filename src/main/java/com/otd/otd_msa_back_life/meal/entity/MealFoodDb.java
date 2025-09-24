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
public class MealFoodDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodDbId;

    @Column(nullable = false, length = 100)
    private String foodName;

    @Column(nullable = false, length = 10)
    private String flag;

    @Positive
    @Column(nullable = false)
    private Integer kcal;

    @Positive
    @Column(nullable = false)
    private Double protein;

    @Positive
    @Column(nullable = false)
    private Double carbohydrate;

    @Positive
    @Column(nullable = false)
    private Double fat;

    @Positive
    @Column(nullable = false)
    private Double sugar;

    @Positive
    @Column(nullable = false)
    private Double natrium;


    @Column(nullable = false, length = 10)
    private int foodCapacity;

}
