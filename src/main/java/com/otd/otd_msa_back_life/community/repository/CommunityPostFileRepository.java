package com.otd.otd_msa_back_life.community.repository;

import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import com.otd.otd_msa_back_life.community.entity.CommunityPostFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityPostFileRepository extends JpaRepository<CommunityPostFile, Long> {

    List<CommunityPostFile> findByPost(CommunityPost post);

    // 특정 게시글에 속한 파일들을 정렬해서 조회
    List<CommunityPostFile> findByPostOrderByOrderIdxAsc(CommunityPost post);

    // 특정 게시글에 속한 파일 개수
    long countByPost(CommunityPost post);

    // 특정 파일을 작성자 ID로 삭제 (보안 체크용)
    void deleteByIdAndUserId(Long id, Long userId);

    // 필요 시 postId 기반으로 직접 조회도 가능
    List<CommunityPostFile> findByPost_PostId(Long postId);
}
