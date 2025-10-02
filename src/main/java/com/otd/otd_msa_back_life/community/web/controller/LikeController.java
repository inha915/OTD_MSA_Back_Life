package com.otd.otd_msa_back_life.community.web.controller;

import com.otd.otd_msa_back_life.community.service.LikeService;
import com.otd.otd_msa_back_life.community.web.dto.like.LikeToggleRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/OTD/community")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 게이트웨이/보안 연동 전: X-MEMBER-ID 헤더로 사용자 식별
    @PostMapping("/posts/{postId}/likes/toggle")
    public LikeToggleRes toggleLike(
            @PathVariable Long postId,
            @RequestHeader("X-MEMBER-ID") Long requesterId
    ) {
        return likeService.toggle(postId, requesterId);
    }
}
