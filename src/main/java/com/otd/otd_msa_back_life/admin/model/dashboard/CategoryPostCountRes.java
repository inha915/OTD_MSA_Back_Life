package com.otd.otd_msa_back_life.admin.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CategoryPostCountRes {
    private String categoryName;
    private Long count;
}
