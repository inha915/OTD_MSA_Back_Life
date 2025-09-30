package com.otd.otd_msa_back_life.body_composition.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class LastestBodyCompositionGetRes {
    private Long measuredId;
    private Long userId;
    private LocalDateTime createdAt;
    private Double height;
    private Double weight;
    private Double skeletalMuscleMass;
    private Double bmi;
    private Double percentBodyFat;
}
