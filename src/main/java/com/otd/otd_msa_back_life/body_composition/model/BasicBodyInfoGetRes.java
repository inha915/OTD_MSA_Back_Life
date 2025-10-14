package com.otd.otd_msa_back_life.body_composition.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class BasicBodyInfoGetRes {
    private Double height;
    private Double weight;
    private Double bmi;
    private Double bmr;
    private LocalDateTime createdAt;

}
