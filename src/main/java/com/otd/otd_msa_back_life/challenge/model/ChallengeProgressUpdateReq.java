package com.otd.otd_msa_back_life.challenge.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ChallengeProgressUpdateReq {
    private Long userId;
    private Long recordId;
    private String name;
    private Double record;
    private LocalDate recordDate;
    private LocalDate today;
}
