package com.otd.otd_msa_back_life.community.web.dto.ment;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class MentRes {
    private Long mentId;
    private Long postId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
}
