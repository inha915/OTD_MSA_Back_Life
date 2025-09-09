package com.otd.otd_msa_back_life.application.otd_community.web.controller;

import com.otd.otd_msa_back_life.application.otd_community.service.PostService;
import com.otd.otd_msa_back_life.application.otd_community.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/community/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public PostRes create(@RequestBody PostCreateReq req) {
        return postService.create(req);
    }

    @GetMapping
    public Page<PostListRes> list(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "20") int size) {
        return postService.list(page, size);
    }

    @GetMapping("/{postId}")
    public PostRes get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    @PutMapping("/{postId}")
    public PostRes update(@PathVariable Long postId,
                          @RequestBody PostUpdateReq req,
                          @RequestHeader("X-MEMBER-ID") Long requesterId) {
        return postService.update(postId, req, requesterId);
    }

    @DeleteMapping("/{postId}")
    public void delete(@PathVariable Long postId,
                       @RequestHeader("X-MEMBER-ID") Long requesterId) {
        postService.deleteSoft(postId, requesterId);
    }
}
