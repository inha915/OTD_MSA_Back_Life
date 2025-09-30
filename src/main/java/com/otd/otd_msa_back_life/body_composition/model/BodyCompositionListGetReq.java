package com.otd.otd_msa_back_life.body_composition.model;


import com.otd.otd_msa_back_life.common.model.DateRangeDto;
import com.otd.otd_msa_back_life.common.model.PagingDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BodyCompositionListGetReq {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer page;
    private Integer rowPerPage;
    private String deviceType;
}
