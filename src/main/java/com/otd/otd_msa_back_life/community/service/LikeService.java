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

    private final CommunityPostRepository postRepository;
    private final CommunityLikeRepository likeRepository;

    @Transactional
    public LikeToggleRes toggle(Long postId, Long userId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));

        var likeOpt = likeRepository.findByPostAndUserId(post, userId);
        boolean liked;
        if (likeOpt.isPresent()) {
            // 이미 좋아요 → 취소
            likeRepository.delete(likeOpt.get());
            post.setLikeCount(Math.max(0, post.getLikeCount() - 1));
            liked = false;
        } else {
            // 좋아요 추가
            CommunityLike like = CommunityLike.builder()
                    .post(post)
                    .userId(userId)
                    .build();
            likeRepository.save(like);
            post.setLikeCount(post.getLikeCount() + 1);
            liked = true;
        }

        // 즉시 반영 원하면 flush (선택)
        // postRepository.save(post);

        return LikeToggleRes.builder()
                .postId(post.getPostId())
                .userId(userId)
                .liked(liked)
                .likeCount(post.getLikeCount())
                .build();
    }
}
