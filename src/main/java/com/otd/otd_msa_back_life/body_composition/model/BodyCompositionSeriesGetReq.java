package com.otd.otd_msa_back_life.body_composition.model;

import com.otd.otd_msa_back_life.common.model.DateRangeDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BodyCompositionSeriesGetReq {
    private DateRangeDto range;     // 조회 범위
    private String deviceType;      // 기기 종류


}

