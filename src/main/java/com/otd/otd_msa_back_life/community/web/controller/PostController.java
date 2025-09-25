package com.otd.otd_msa_back_life.community.web.controller;

import com.otd.otd_msa_back_life.community.service.PostService;
import com.otd.otd_msa_back_life.community.web.dto.post.PostCreateReq;
import com.otd.otd_msa_back_life.community.web.dto.post.PostListRes;
import com.otd.otd_msa_back_life.community.web.dto.post.PostRes;
import com.otd.otd_msa_back_life.community.web.dto.post.PostUpdateReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/OTD/community")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    // 생성 (JSON 본문)
    @PostMapping("/posts")
    public ResponseEntity<PostRes> create(
            @Valid @RequestBody PostCreateReq req,
            @RequestHeader("X-MEMBER-ID") Long requesterId,
            UriComponentsBuilder uriBuilder
    ) {
        PostRes res = postService.create(req, requesterId);
        return ResponseEntity
                .created(uriBuilder.path("/api/OTD/community/posts/{id}")
                        .buildAndExpand(res.getPostId()).toUri())
                .body(res);
    }

    // 목록
    @GetMapping("/posts")
    public Page<PostListRes> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String categoryKey,
            @RequestParam(required = false) String searchText
    ) {
        return postService.list(page, size, categoryKey, searchText);
    }


    // 상세
    @GetMapping("/posts/{postId}")
    public PostRes get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    // 수정
    @PutMapping("/posts/{postId}")
    public PostRes update(
            @PathVariable Long postId,
            @RequestBody PostUpdateReq req,
            @RequestHeader("X-MEMBER-ID") Long requesterId
    ) {
        return postService.update(postId, req, requesterId);
    }

    // 삭제
    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId,
                       @RequestHeader("X-MEMBER-ID") Long requesterId) {
        postService.deleteSoft(postId, requesterId);
    }
}