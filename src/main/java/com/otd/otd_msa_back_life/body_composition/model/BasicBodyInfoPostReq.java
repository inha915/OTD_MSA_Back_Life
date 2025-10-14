package com.otd.otd_msa_back_life.body_composition.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BasicBodyInfoPostReq {
    @Positive(message = "키는 0보다 커야 합니다.")
    @Max(value = 300, message = "키는 300cm 이하로 입력해주세요.")
    private Double height;

    @Positive(message = "몸무게는 0보다 커야 합니다.")
    @Max(value = 500, message = "몸무게는 500kg 이하로 입력해주세요.")
    private Double weight;

    @Positive(message = "BMI는 0보다 커야 합니다.")
    @DecimalMax(value = "100.0", message = "BMI는 100 이하로 입력해주세요.")
    private Double bmi;

    @Positive(message = "기초대사량은 0보다 커야 합니다.")
    @DecimalMax(value = "10000.0", message = "BMR은 10,000 이하로 입력해주세요.")
    private Double bmr;

}
