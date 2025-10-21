package com.otd.otd_msa_back_life.mypage.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 마이페이지 - 댓글 목록 응답 DTO
 * (내가 작성한 댓글 정보 + 어느 게시글에 달았는지 정보 포함)
 */
@Getter
@Builder
public class MyCommentRes {

    /** 댓글 ID */
    private Long commentId;

    /** 댓글 내용 */
    private String content;

    /** 댓글 작성일시 */
    private LocalDateTime createdAt;

    /** 연결된 게시글 ID */
    private Long postId;

    /** 연결된 게시글 제목 */
    private String postTitle;

    /** 게시글 작성자 ID */
    private Long postAuthorId;
}