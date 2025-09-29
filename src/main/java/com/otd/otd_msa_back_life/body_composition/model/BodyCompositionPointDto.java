package com.otd.otd_msa_back_life.body_composition.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
@ToString
public class BodyCompositionPointDto {  // 날짜별로 여러 지표를 한꺼번에 내려주기 위함
    private LocalDate date;     // 선택 날짜
    private Map<String, Double> values;       // 키, 값 구성 {"weight": 68, "bmi": 22}
}
