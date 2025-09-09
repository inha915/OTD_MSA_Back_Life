package com.otd.otd_msa_back_life.otd_community.web.dto.post;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class PostListRes {
    private Long postId;
    private String title;
    private Long memberNoLogin;
    private Integer likeCount;
    private LocalDateTime createdAt;
}
