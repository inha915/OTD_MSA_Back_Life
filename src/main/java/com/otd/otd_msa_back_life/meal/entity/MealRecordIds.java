package com.otd.otd_msa_back_life.meal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.otd.otd_msa_back_life.meal.enums.EnumMealTime;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
@ToString
public class MealRecordIds implements Serializable {

    @Column(nullable = false, length = 10)
    private String  mealTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate mealDay;

}
