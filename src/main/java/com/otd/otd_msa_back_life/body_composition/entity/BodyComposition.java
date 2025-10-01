package com.otd.otd_msa_back_life.body_composition.entity;

import com.otd.otd_msa_back_life.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BodyComposition extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measuredId;  // 측정기록 PK

    @Column(nullable = false)
    Long userId;            // 사용자 PK

    @Column(nullable = false)
    Double weight;          // 체중

    @Column(nullable = false)
    Double height;          // 신장

    @Column(nullable = false)
    Double totalBodyWater;  // 체수분

    @Column(nullable = false)
    Double protein;         // 단백질

    @Column(nullable = false)
    Double mineral;         // 무기질

    @Column(nullable = false)
    Double bodyFatMass;     // 체지방량

    @Column(nullable = false)
    Double skeletalMuscleMass;  // 골격근량

    @Column(nullable = false)
    Double bmi;                 // 체질량지수

    @Column(nullable = false)
    Double percentBodyFat;      // 체지방률

    @Column(nullable = false)
    Double basalMetabolicRate;  // 기초대사량

    @Column(nullable = false, length = 20)
    String deviceType;          // 인바디 기기타입


    // metricCode 기반 값 반환 메서드
    public Double getValueByMetricCode(String metricCode) {
        return switch (metricCode) {
            case "weight" -> getWeight();
            case "bmi" -> getBmi();
            case "skeletal_muscle_mass" -> getSkeletalMuscleMass();
            case "body_fat_mass" -> getBodyFatMass();
            case "percent_body_fat" -> getPercentBodyFat();
            case "basal_metabolic_rate" -> getBasalMetabolicRate();
            case "total_body_water" -> getTotalBodyWater();
            case "protein" -> getProtein();
            case "mineral" -> getMineral();
            default -> null; // 해당 metricCode가 없으면 null 반환
        };
    }

}
