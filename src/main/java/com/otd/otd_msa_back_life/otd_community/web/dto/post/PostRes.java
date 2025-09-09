package com.otd.otd_msa_back_life.otd_community.web.dto.post;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class PostRes {
    private Long postId;
    private Long memberNoLogin;
    private String title;
    private String content;
    private Integer likeCount;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
