package com.otd.otd_msa_back_life.community.web.dto.like;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeToggleRes {
    private Long postId;
    private Long userId;
    private boolean liked;
    private int likeCount;
}
