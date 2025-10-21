package com.otd.otd_msa_back_life.body_composition.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.otd.otd_msa_back_life.common.model.DateRangeDto;
import com.otd.otd_msa_back_life.common.model.PagingDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BodyCompositionListGetReq {
    private Integer page;
    private Integer rowPerPage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String deviceType;

    @ConstructorProperties({"page", "row_per_page", "start_date", "end_date", "device_type"})
    public BodyCompositionListGetReq(
            Integer page, Integer rowPerPage, LocalDateTime startDate, LocalDateTime endDate, String deviceType) {
        this.page = page;
        this.rowPerPage = rowPerPage;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
