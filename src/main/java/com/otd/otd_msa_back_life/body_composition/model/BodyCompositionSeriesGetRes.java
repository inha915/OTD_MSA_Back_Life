package com.otd.otd_msa_back_life.body_composition.model;

import com.otd.otd_msa_back_life.common.model.DateRangeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class BodyCompositionSeriesGetRes {
    private String deviceType;      // 기기 종류
    private List<BodyCompositionPointDto> points;  // 날짜별 측정값
    private DateRangeDto range;                     // 조회 날짜 범위
}
