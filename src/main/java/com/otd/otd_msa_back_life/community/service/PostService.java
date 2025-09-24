package com.otd.otd_msa_back_life.community.service;

import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import com.otd.otd_msa_back_life.community.repository.CommunityPostRepository;
import com.otd.otd_msa_back_life.community.web.dto.post.PostCreateReq;
import com.otd.otd_msa_back_life.community.web.dto.post.PostListRes;
import com.otd.otd_msa_back_life.community.web.dto.post.PostRes;
import com.otd.otd_msa_back_life.community.web.dto.post.PostUpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final CommunityPostRepository postRepository;

    /** 컨트롤러의 create(@Valid PostCreateReq, @RequestHeader Long requesterId)와 매칭 */
    @Transactional
    public PostRes create(PostCreateReq req, Long requesterId) {
        CommunityPost post = CommunityPost.builder()
                .userId(requesterId) // 헤더/토큰에서 받은 작성자 ID 사용
                .title(req.getTitle())
                .content(req.getContent())
                .likeCount(0)
                .isDeleted(false)
                .build();

        CommunityPost saved = postRepository.save(post);
        return toRes(saved);
    }

    @Transactional(readOnly = true)
    public Page<PostListRes> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "postId"));
        return postRepository.findAll(pageable)
                .map(p -> PostListRes.builder()
                        .postId(p.getPostId())
                        .title(p.getTitle())
                        .userId(p.getUserId())
                        .likeCount(p.getLikeCount())
                        .createdAt(p.getCreatedAt())
                        .build());
    }

    @Transactional(readOnly = true)
    public PostRes get(Long postId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));
        return toRes(post);
    }

    @Transactional
    public PostRes update(Long postId, PostUpdateReq req, Long requesterId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));

        if (!post.getUserId().equals(requesterId)) {
            throw new IllegalStateException("수정 권한 없음");
        }

        post.setTitle(req.getTitle());
        post.setContent(req.getContent());
        return toRes(post);
    }

    @Transactional
    public void deleteSoft(Long postId, Long requesterId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));

        if (!post.getUserId().equals(requesterId)) {
            throw new IllegalStateException("삭제 권한 없음");
        }

        post.setIsDeleted(true);
    }

    private PostRes toRes(CommunityPost p) {
        return PostRes.builder()
                .postId(p.getPostId())
                .userId(p.getUserId())
                .title(p.getTitle())
                .content(p.getContent())
                .likeCount(p.getLikeCount())
                .isDeleted(p.getIsDeleted())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();
    }
}
