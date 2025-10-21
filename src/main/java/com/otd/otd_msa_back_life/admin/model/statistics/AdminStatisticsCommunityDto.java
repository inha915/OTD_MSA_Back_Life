package com.otd.otd_msa_back_life.admin.model.statistics;

import com.otd.otd_msa_back_life.admin.model.dashboard.CategoryPostCountRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AdminStatisticsCommunityDto {
    private List<CategoryPostCountRes> categoryPostCount;
    private List<PostCountRes> postCount;
}
