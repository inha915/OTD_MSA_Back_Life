package com.otd.otd_msa_back_life.exercise.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AverageExerciseDurationDto {
    private LocalDate date;
    private Double averageDuration;

}
