package com.otd.otd_msa_back_life.community.web.dto.category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class CategoryReq {
    private String categoryKey;
    private String name;
}
