package com.otd.otd_msa_back_life.common.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DateRangeDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;


}
