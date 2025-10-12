package com.otd.otd_msa_back_life.admin.model.dashboard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TopCommentPostRes {
    private Long postId;
    private String categoryName;
    private Long commentCount;
    private String title;
    private LocalDateTime createdAt;
}
