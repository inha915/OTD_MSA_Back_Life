package com.otd.otd_msa_back_life.community.web.controller;

import com.otd.otd_msa_back_life.community.service.LikeService;
import com.otd.otd_msa_back_life.community.web.dto.like.LikeToggleRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/posts/{postId}/like/toggle")
    public LikeToggleRes toggle(@PathVariable Long postId,
                                @RequestHeader("X-MEMBER-ID") Long userId) {
        return likeService.toggle(postId, userId);
    }
}
