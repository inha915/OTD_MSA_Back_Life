package com.otd.otd_msa_back_life.community.repository;

import com.otd.otd_msa_back_life.community.entity.CommunityLike;
import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
    Optional<CommunityLike> findByPostAndUserId(CommunityPost post, Long userId);
    long countByPost(CommunityPost post);

    void deleteAllByUserId(Long userId);
}
