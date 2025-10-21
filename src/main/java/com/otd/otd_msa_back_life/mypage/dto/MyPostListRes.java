package com.otd.otd_msa_back_life.mypage.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 마이페이지 - 게시글 목록 응답 DTO
 * (내가 작성한 게시글, 내가 좋아요한 게시글에서 공통 사용)
 */
@Getter
@Builder
public class MyPostListRes {

    /** 게시글 ID */
    private Long postId;

    /** 작성자 ID */
    private Long userId;

    /** 제목 */
    private String title;

    /** 내용 요약 (140자) */
    private String content;

    /** 좋아요 수 */
    private Integer likeCount;

    /** 댓글 수 */
    private Integer commentCount;

    /** 카테고리 키 (free, diet, work, love) */
    private String categoryKey;

    /** 카테고리 이름 (자유수다, 다이어트 등) */
    private String categoryName;

    /** 생성일시 */
    private LocalDateTime createdAt;

    /** 수정일시 */
    private LocalDateTime updatedAt;
}