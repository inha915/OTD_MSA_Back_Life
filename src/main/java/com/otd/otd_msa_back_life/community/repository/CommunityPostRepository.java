package com.otd.otd_msa_back_life.community.repository;

import com.otd.otd_msa_back_life.admin.model.dashboard.CategoryPostCountRes;
import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {

    // 카테고리 ID로 사용중인 게시글 수 카운트
    long countByCategory_CategoryId(Long categoryId);

    // 기본 목록: 삭제되지 않은 글만
    Page<CommunityPost> findByIsDeletedFalse(Pageable pageable);

    // 카테고리 필터: 삭제되지 않은 글만
    Page<CommunityPost> findByIsDeletedFalseAndCategory_CategoryKey(String categoryKey, Pageable pageable);

    // 검색(제목/내용) : 삭제되지 않은 글만
    @Query("""
           select p
           from CommunityPost p
           where p.isDeleted = false
             and (
                lower(p.title)   like lower(concat('%', :q, '%'))
                or lower(p.content) like lower(concat('%', :q, '%'))
             )
           """)
    Page<CommunityPost> search(@Param("q") String q, Pageable pageable);

    // 카테고리 + 검색(제목/내용)
    @Query("""
           select p
           from CommunityPost p
           where p.isDeleted = false
             and p.category.categoryKey = :categoryKey
             and (
                lower(p.title)   like lower(concat('%', :q, '%'))
                or lower(p.content) like lower(concat('%', :q, '%'))
             )
           """)
    Page<CommunityPost> searchInCategory(@Param("categoryKey") String categoryKey,
                                         @Param("q") String q,
                                         Pageable pageable);

    @Modifying
    @Query("UPDATE CommunityPost p SET p.isDeleted = true WHERE p.userId = :userId")
    int softDeleteByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE CommunityPost p SET p.isDeleted = true WHERE p.postId = :postId")
    int softDeleteByPostId(Long postId);
    //사용자가 작성한 게시글 목록
    Page<CommunityPost> findByUserIdAndIsDeletedFalse(Long userId, Pageable pageable);

    //사용자가 작성한 게시글 총 개수
    long countByUserIdAndIsDeletedFalse(Long userId);

    // 대시보드
    // 총 게시글 수
    @Query("SELECT COUNT(p) FROM CommunityPost p WHERE p.isDeleted = false")
    int countAllPost();

    // 이번 주 신규 게시글 수
    @Query("SELECT COUNT(p) FROM CommunityPost p WHERE p.isDeleted = false AND p.createdAt >= :monday")
    int countWeeklyPost(@Param("monday") LocalDateTime monday);

    // 카테고리별 게시글 수
    @Query("""
        SELECT new com.otd.otd_msa_back_life.admin.model.dashboard.CategoryPostCountRes(c.name, COUNT(p))
        FROM CommunityPost p
        JOIN p.category c
        WHERE p.isDeleted = false
        GROUP BY c.name
        ORDER BY COUNT(p) DESC
    """)
    List<CategoryPostCountRes> countPostByCategory();

    @Query("update CommunityPost c set c.nickName = :nickname where c.userId = :userId")
    @Modifying
    int updateNickNameByUserId(Long userId, String nickname);

    @Query("update CommunityPost c set c.profile = :profile where c.userId = :userId")
    @Modifying
    int updateProfileByUserId(String profile, Long userId);

    // CommunityPostRepository.java
    Optional<CommunityPost> findTopByUserIdOrderByPostIdDesc(Long userId);

}

