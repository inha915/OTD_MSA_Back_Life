package com.otd.otd_msa_back_life.meal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DailyWaterIntake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyWaterIntakeId;

    private Long memberId;

    private LocalDateTime intakeDate;
    private Double amountLiter;

}
