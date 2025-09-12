package com.otd.otd_msa_back_life.exercise.entity;

import com.otd.otd_msa_back_life.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ExerciseRecord extends BaseTimeEntity {
    // createdAt, updatedAt

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long exerciseRecordId;      // 운동기록 pk

    @ManyToOne
    @JoinColumn(nullable = false, name = "exercise_id")
    private ExerciseCatalog exercise;       // 운동 종목 pk

    private Long memberId;                  // 사용자 pk

    @Column(nullable = false)
    private Integer effortLevel;            // 노력 강도 (1~10)

    @Column(nullable = false)
    private Integer activityKcal;           // 활동 에너지

    @Column(nullable = false)
    private LocalDateTime startAt;          // 운동 시작 시점

    @Column(nullable = false)
    private LocalDateTime endAt;            // 운동 종료 시점


}