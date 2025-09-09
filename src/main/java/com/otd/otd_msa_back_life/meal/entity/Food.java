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
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @Column(nullable = false, length = 100)
    private String foodName;

    @Column(nullable = false, length = 30)
    private String foodCategory;

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

}
