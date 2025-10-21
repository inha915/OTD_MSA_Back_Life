package com.otd.otd_msa_back_life.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AdminCommunityGetRes {
    private Long postId;
    private Long userId;
    private String nickName;
    private String category;
    private String title;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
