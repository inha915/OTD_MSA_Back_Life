package com.otd.otd_msa_back_life.feign.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeRecordDeleteReq {
    private Long userId;
    private String name;
    private Long recordId;
    private LocalDate recordDate;
    private LocalDate today;
    private int count;
    private int totalKcal;
}

