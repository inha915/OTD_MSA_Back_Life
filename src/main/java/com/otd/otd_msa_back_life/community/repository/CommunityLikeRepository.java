package com.otd.otd_msa_back_life.community.repository;

import com.otd.otd_msa_back_life.community.entity.CommunityLike;
import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
    Optional<CommunityLike> findByPostAndUserId(CommunityPost post, Long userId);
    long countByPost(CommunityPost post);

    void deleteAllByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM CommunityLike l WHERE l.post.postId = :postId")
    int deleteByPostId(Long postId);
    //사용자가 좋아요한 게시글 목록
    @Query("""
        SELECT l FROM CommunityLike l
        JOIN FETCH l.post p
        JOIN FETCH p.category
        WHERE l.userId = :userId
        AND p.isDeleted = false
        """)
    Page<CommunityLike> findByUserIdWithPost(@Param("userId") Long userId, Pageable pageable);
    //사용자가 좋아요한 게시글 총 개수
    long countByUserId(Long userId);
    List<CommunityLike> post(CommunityPost post);
}
