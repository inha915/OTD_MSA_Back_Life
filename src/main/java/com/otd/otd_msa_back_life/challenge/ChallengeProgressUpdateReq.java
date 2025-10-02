package com.otd.otd_msa_back_life.challenge;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ChallengeProgressUpdateReq {
    private Long userId;
    private String name;
    private Double record;
    private LocalDate recordDate;
    private LocalDate today;
}
