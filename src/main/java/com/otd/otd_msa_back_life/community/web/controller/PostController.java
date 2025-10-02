package com.otd.otd_msa_back_life.community.web.controller;

import com.otd.otd_msa_back_life.community.service.PostService;
import com.otd.otd_msa_back_life.community.service.PostFileService;
import com.otd.otd_msa_back_life.community.web.dto.file.PostFileRes;
import com.otd.otd_msa_back_life.community.web.dto.post.PostCreateReq;
import com.otd.otd_msa_back_life.community.web.dto.post.PostListRes;
import com.otd.otd_msa_back_life.community.web.dto.post.PostRes;
import com.otd.otd_msa_back_life.community.web.dto.post.PostUpdateReq;
import com.otd.otd_msa_back_life.configuration.model.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/OTD/community")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostFileService postFileService;

    // 생성
    @PostMapping("/posts")
    public ResponseEntity<PostRes> create(
            @Valid @RequestBody PostCreateReq req,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            UriComponentsBuilder uriBuilder
    ) {
        Long requesterId = userPrincipal.getSignedUserId();
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

    // 해당 게시글의 파일 목록 조회
    @GetMapping("/posts/{postId}/files")
    public List<PostFileRes> listFiles(@PathVariable Long postId) {
        return postFileService.listByPost(postId);
    }

    // 수정
    @PutMapping("/posts/{postId}")
    public PostRes update(
            @PathVariable Long postId,
            @RequestBody PostUpdateReq req,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Long requesterId = userPrincipal.getSignedUserId();
        return postService.update(postId, req, requesterId);
    }

    // 삭제
    @DeleteMapping("/posts/{postId}")
    public void delete(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Long requesterId = userPrincipal.getSignedUserId();
        postService.deleteSoft(postId, requesterId);
    }
}
