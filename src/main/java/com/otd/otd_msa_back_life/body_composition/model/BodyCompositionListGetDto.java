package com.otd.otd_msa_back_life.body_composition.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class BodyCompositionListGetDto {
    private Integer startIdx;
    private Integer size;
    private Long userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String deviceType;
}
