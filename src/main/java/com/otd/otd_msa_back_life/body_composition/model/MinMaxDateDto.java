package com.otd.otd_msa_back_life.body_composition.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Lombok 사용 예시
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MinMaxDateDto {
    // 사용자의 가장 오래된 기록일
    private LocalDateTime startDate;

    // 사용자의 가장 최근 기록일
    private LocalDateTime endDate;
}