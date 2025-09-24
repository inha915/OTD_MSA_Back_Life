package com.otd.otd_msa_back_life.body_composition.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class LastestBodyCompositionGetRes {
    private Long measured_id;
    private Long user_id;
    private LocalDateTime cratedAt;
    private Double height;
    private Double weight;
    private Double skeletalMuscleMass;
    private Double Bmi;
    private Double PercentBodyFat;
}
