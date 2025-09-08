package com.otd.otd_msa_back_life.application.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingDto {
    private Integer startIdx;
    private Integer size;
}