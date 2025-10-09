package com.otd.otd_msa_back_life.community.web.dto.post;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class PostListRes {
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private Integer likeCount;
    private LocalDateTime createdAt;
    private String categoryKey;
    private String categoryName;
    private Integer commentCount;
}
