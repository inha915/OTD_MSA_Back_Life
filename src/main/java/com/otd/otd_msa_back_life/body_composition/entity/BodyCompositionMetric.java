package com.otd.otd_msa_back_life.body_composition.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BodyCompositionMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metricId;      // PK

    @Column(nullable = false, length = 20)
    private String metricCode;  // 지표 이름 (weight, bmi, ...)

    @Column(nullable = false, length = 10)
    private String metricName;  // 화면 표시용 지표명 (체중, 골격근량, ...)

    @Column(length = 8)
    private String unit;        // 지표 단위

    @Column(nullable = false, length = 20)
    private String description; // 설명
}
