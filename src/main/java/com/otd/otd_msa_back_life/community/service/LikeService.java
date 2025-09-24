package com.otd.otd_msa_back_life.community.service;

import com.otd.otd_msa_back_life.community.entity.CommunityLike;
import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import com.otd.otd_msa_back_life.community.repository.CommunityLikeRepository;
import com.otd.otd_msa_back_life.community.repository.CommunityPostRepository;
import com.otd.otd_msa_back_life.community.web.dto.like.LikeToggleRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final CommunityLikeRepository likeRepository;
    private final CommunityPostRepository postRepository;

    @Transactional
    public LikeToggleRes toggle(Long postId, Long memberId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));

        var existing = likeRepository.findByPostAndMemberId(post, memberId);
        boolean likedNow;
        if (existing.isPresent()) {
            likeRepository.delete(existing.get());
            post.setLikeCount(Math.max(0, post.getLikeCount() - 1));
            likedNow = false;
        } else {
            likeRepository.save(CommunityLike.builder()
                    .post(post)
                    .memberId(memberId)
                    .build());
            post.setLikeCount(post.getLikeCount() + 1);
            likedNow = true;
        }
        return LikeToggleRes.builder()
                .postId(postId)
                .memberId(memberId)
                .liked(likedNow)
                .likeCount(post.getLikeCount())
                .build();
    }
}
