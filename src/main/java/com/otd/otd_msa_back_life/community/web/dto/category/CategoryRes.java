package com.otd.otd_msa_back_life.community.web.dto.category;

import com.otd.otd_msa_back_life.community.entity.CommunityCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRes {
    private Long categoryId;
    private String categoryKey;
    private String name;

    public static CategoryRes from(CommunityCategory category) {
        return CategoryRes.builder()
                .categoryId(category.getCategoryId())
                .categoryKey(category.getCategoryKey())
                .name(category.getName())
                .build();
    }
}
