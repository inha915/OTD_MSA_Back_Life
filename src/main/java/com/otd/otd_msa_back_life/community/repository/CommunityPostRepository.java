package com.otd.otd_msa_back_life.community.repository;

import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

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
}
