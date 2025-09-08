package com.otd.otd_msa_back_life.application.exercise_record.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class ExerciseRecordPostReq {

    @NotNull(message = "운동 선택 필수")
    private Long exerciseId;

    @NotNull(message = "소모된 활동 에너지 입력은 필수")
    @Positive(message = "소모된 활동 에너지는 양수여야 합니다")
    private Integer activityKcal;

    @NotNull(message = "운동일자 입력은 필수")
    @PastOrPresent(message = "운동일자는 과거나 현재여야 합니다")
    private LocalDateTime startDatetime;

    @NotNull(message = "운동일자 입력은 필수")
    @PastOrPresent(message = "운동일자는 과거나 현재여야 합니다")
    private LocalDateTime endDatetime;


    @NotNull(message = "운동 소요 시간 입력은 필수")
    @Positive(message = "운동 소요 시간은 양수여야 합니다")
    private Integer exerciseDuration;

    @NotNull(message = "운동강도 입력은 필수")
    @Min(value = 1, message = "운동강도는 최소 1 이상이어야 합니다")
    @Max(value = 10, message = "운동강도는 최대 10 이하여야 합니다")
    private Integer effortLevel;
}

