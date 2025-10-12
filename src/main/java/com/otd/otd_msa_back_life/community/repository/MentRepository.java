package com.otd.otd_msa_back_life.community.repository;

import com.otd.otd_msa_back_life.community.entity.Ment;
import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MentRepository extends JpaRepository<Ment, Long> {
    List<Ment> findByPostOrderByCreatedAtAsc(CommunityPost post);
    long countByPost(CommunityPost post);

    void deleteAllByUserId(Long userId);


    //사용자가 작성한 댓글목록
    @Query("""
        SELECT m FROM Ment m
        JOIN FETCH m.post p
        WHERE m.userId = :userId
        ORDER BY m.createdAt DESC
        """)
    Page<Ment> findByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId, Pageable pageable);

    //사용자가 작성한 댓글 총개수
    long countByUserId(Long userId);
}
