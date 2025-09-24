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
@RequestMapping("/api/v1/community/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostRes> create(@Valid @RequestBody PostCreateReq req,
                                          @RequestHeader("X-MEMBER-ID") Long requesterId,
                                          UriComponentsBuilder uriBuilder) {
        PostRes res = postService.create(req, requesterId);
        return ResponseEntity
                .created(uriBuilder.path("/api/v1/community/posts/{id}")
                        .buildAndExpand(res.getPostId()).toUri())
                .body(res);
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
