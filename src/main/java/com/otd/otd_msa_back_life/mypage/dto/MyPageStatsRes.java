package com.otd.otd_msa_back_life.mypage.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 마이페이지 통계 응답 DTO
 * (내가 작성한 게시글 수, 좋아요한 게시글 수, 작성한 댓글 수)
 */
@Getter
@Builder
public class MyPageStatsRes {

    /** 내가 작성한 게시글 총 개수 */
    private Long totalPosts;

    /** 내가 좋아요한 게시글 총 개수 */
    private Long totalLikedPosts;

    /** 내가 작성한 댓글 총 개수 */
    private Long totalComments;
}