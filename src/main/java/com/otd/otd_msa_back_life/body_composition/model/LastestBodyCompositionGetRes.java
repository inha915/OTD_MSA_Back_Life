package com.otd.otd_msa_back_life.body_composition.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
