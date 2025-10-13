package com.otd.otd_msa_back_life.mypage.web.controller;

import com.otd.otd_msa_back_life.configuration.model.UserPrincipal;
import com.otd.otd_msa_back_life.mypage.dto.MyCommentRes;
import com.otd.otd_msa_back_life.mypage.dto.MyPageStatsRes;
import com.otd.otd_msa_back_life.mypage.dto.MyPostListRes;
import com.otd.otd_msa_back_life.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/OTD/community/my")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    /**
     * 내가 작성한 게시글 목록
     * GET /api/OTD/mypage/posts?page=0&size=10
     */
    @GetMapping("/posts")
    public Page<MyPostListRes> getMyPosts(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Long userId = userPrincipal.getSignedUserId();
        return myPageService.getMyPosts(userId, page, size);
    }

    /**
     * 내가 좋아요한 게시글 목록
     * GET /api/OTD/mypage/liked-posts?page=0&size=10
     */
    @GetMapping("/liked-posts")
    public Page<MyPostListRes> getMyLikedPosts(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Long userId = userPrincipal.getSignedUserId();
        return myPageService.getMyLikedPosts(userId, page, size);
    }

    /**
     * 내가 작성한 댓글 목록
     * GET /api/OTD/mypage/comments?page=0&size=10
     */
    @GetMapping("/comments")
    public Page<MyCommentRes> getMyComments(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Long userId = userPrincipal.getSignedUserId();
        return myPageService.getMyComments(userId, page, size);
    }

    /**
     * 마이페이지 통계 조회
     * GET /api/OTD/mypage/stats
     */
    @GetMapping("/stats")
    public MyPageStatsRes getMyStats(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Long userId = userPrincipal.getSignedUserId();
        return myPageService.getMyStats(userId);
    }
}