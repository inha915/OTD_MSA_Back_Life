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
@ToString
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
    @JoinColumn(name = "food_id", foreignKey = @ForeignKey(name = "fk_meal_record_food"))
    private MealFoodDb foodDb;

    @ManyToOne
    @JoinColumn(name = "user_food_id", foreignKey = @ForeignKey(name = "fk_meal_record_user_food"))
    private MealFoodMakeDb userFood;

    @PrePersist
    @PreUpdate
    void validate() {
        if ((foodDb == null) == (userFood == null)) throw new IllegalStateException("Exactly one must be set");
    }
    @Embedded
    private MealRecordIds mealRecordIds;

}
