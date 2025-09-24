package com.otd.otd_msa_back_life.configuration.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter

@AllArgsConstructor
public class ResultResponse<T> {
    private String message;
    private T result;
}