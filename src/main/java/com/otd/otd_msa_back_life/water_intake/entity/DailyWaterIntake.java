package com.otd.otd_msa_back_life.water_intake.entity;

import com.otd.otd_msa_back_life.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DailyWaterIntake extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyWaterIntakeId;


    private Double amountLiter;

    @Column(nullable = false, unique = true)
    private Long memberId;

    @Column(nullable = false , unique = true)
    private LocalDateTime intakeDate;


}
