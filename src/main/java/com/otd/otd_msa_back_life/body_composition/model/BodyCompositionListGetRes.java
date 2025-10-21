package com.otd.otd_msa_back_life.body_composition.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class BodyCompositionListGetRes {
    private LocalDateTime createdAt;
    private String deviceType;
}
