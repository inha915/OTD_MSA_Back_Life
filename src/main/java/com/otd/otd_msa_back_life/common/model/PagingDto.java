package com.otd.otd_msa_back_life.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingDto {
    private String type;
    private String date;
    private Integer startIdx;
    private Integer size;
    private Long userId;
}