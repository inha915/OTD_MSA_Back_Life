package com.otd.otd_msa_back_life.common.model;


import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

import java.time.LocalDate;

@Getter
@ToString
public class PagingReq {
    private Integer page;
    private Integer rowPerPage;
    private String type;
    private String date;

    public PagingReq(Integer page, @BindParam("row_per_page") Integer rowPerPage, String type, String date) {
        this.page = page;
        this.rowPerPage = rowPerPage;
        this.type = type;
        this.date = date;
    }
}
