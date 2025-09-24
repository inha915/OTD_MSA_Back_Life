package com.otd.otd_msa_back_life.exercise.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ExerciseCatalog {
    @Id
    private Long exerciseId;                // 운동종목 id

    @Column(nullable = false, length = 30)
    private String exerciseName;           // 운동종목

    @Column(nullable = false)
    private Double exerciseMet;           // 활동 에너지 계산할 때 사용할 MET

    @Column(nullable = false)
    private Boolean hasDistance;        // 거리기반운동여부

    @Column(nullable = false)           // 반복횟수운동여부
    private Boolean hasReps;
}