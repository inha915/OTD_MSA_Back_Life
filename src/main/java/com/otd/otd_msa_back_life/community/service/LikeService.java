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

    @Transactional
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));

            post.setLikeCount(Math.max(0, post.getLikeCount() - 1));
        } else {
                    .post(post)
            post.setLikeCount(post.getLikeCount() + 1);
        }
        return LikeToggleRes.builder()
                .likeCount(post.getLikeCount())
                .build();
    }
}
