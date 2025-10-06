package com.otd.otd_msa_back_life.exercise.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class ExerciseRecordPostReq {

//    운동 종목
    @NotNull(message = "운동 선택 필수")
    private Long exerciseId;

//    활동 에너지
    @NotNull(message = "소모된 활동 에너지 입력은 필수")
    @Positive(message = "소모된 활동 에너지는 양수여야 합니다")
    private Integer activityKcal;

//    운동 시작 시점
    @NotNull(message = "운동시작시점 입력은 필수")
    @PastOrPresent(message = "운동시작시점는 과거나 현재여야 합니다")
    private LocalDateTime startAt;

//    운동종료시점
    @NotNull(message = "운동종료시점 입력은 필수")
    @PastOrPresent(message = "운동종료시점은 과거나 현재여야 합니다")
    private LocalDateTime endAt;

//    운동소요시간
@Positive(message = "소모된 활동 에너지는 양수여야 합니다")
    private Integer duration;

//    운동 강도
    @NotNull(message = "운동강도 입력은 필수")
    @Min(value = 1, message = "운동강도는 최소 1 이상이어야 합니다")
    @Max(value = 10, message = "운동강도는 최대 10 이하여야 합니다")
    private Integer effortLevel;

//    운동거리
//    @Null(message = "거리 기반 운동이 아닙니다.")
    @DecimalMin(value = "0.01", message = "거리는 0.01km 이상이어야 합니다.")
    private Double distance;

    //    반복횟수
    @Min(value = 1, message = "반복횟수는 최소 1 이상이어야 합니다")
    private Integer reps;

//   운동 종료 시점 validation
    @AssertTrue(message = "종료일시는 시작일시 이후여야 합니다. ")
    public boolean isEndAfterStart() {
        return endAt == null || startAt == null || !endAt.isBefore(startAt);
    }
}

