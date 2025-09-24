package com.otd.otd_msa_back_life.exercise.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ExerciseRecordWeeklyGetReq {


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startOfWeek;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endOfWeek;

    public ExerciseRecordWeeklyGetReq(LocalDateTime startOfWeek, LocalDateTime endOfWeek) {
        this.startOfWeek = startOfWeek;
        this.endOfWeek = endOfWeek;
    }

}
