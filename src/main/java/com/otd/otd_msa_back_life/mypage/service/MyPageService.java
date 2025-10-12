package com.otd.otd_msa_back_life.mypage.service;

import com.otd.otd_msa_back_life.community.entity.CommunityLike;
import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import com.otd.otd_msa_back_life.community.entity.Ment;
import com.otd.otd_msa_back_life.community.repository.CommunityLikeRepository;
import com.otd.otd_msa_back_life.community.repository.CommunityPostRepository;
import com.otd.otd_msa_back_life.community.repository.MentRepository;
import com.otd.otd_msa_back_life.mypage.dto.MyCommentRes;
import com.otd.otd_msa_back_life.mypage.dto.MyPageStatsRes;
import com.otd.otd_msa_back_life.mypage.dto.MyPostListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final CommunityPostRepository postRepository;
    private final CommunityLikeRepository likeRepository;
    private final MentRepository mentRepository;

    /**
     * 내가 작성한 게시글 목록 조회
     */
    public Page<MyPostListRes> getMyPosts(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "postId"));

        Page<CommunityPost> posts = postRepository.findByUserIdAndIsDeletedFalse(userId, pageable);

        return posts.map(this::toMyPostListRes);
    }

    /**
     * 내가 좋아요한 게시글 목록 조회
     */
    public Page<MyPostListRes> getMyLikedPosts(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        Page<CommunityLike> likes = likeRepository.findByUserIdWithPost(userId, pageable);

        return likes.map(like -> toMyPostListRes(like.getPost()));
    }

    /**
     * 내가 작성한 댓글 목록 조회
     */
    public Page<MyCommentRes> getMyComments(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "commentId"));

        Page<Ment> comments = mentRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);

        return comments.map(this::toMyCommentRes);
    }

    /**
     * 마이페이지 통계 조회
     */
    public MyPageStatsRes getMyStats(Long userId) {
        long totalPosts = postRepository.countByUserIdAndIsDeletedFalse(userId);
        long totalLikes = likeRepository.countByUserId(userId);
        long totalComments = mentRepository.countByUserId(userId);

        return MyPageStatsRes.builder()
                .totalPosts(totalPosts)
                .totalLikedPosts(totalLikes)
                .totalComments(totalComments)
                .build();
    }


    /**
     * CommunityPost -> MyPostListRes 변환
     */
    private MyPostListRes toMyPostListRes(CommunityPost p) {
        return MyPostListRes.builder()
                .postId(p.getPostId())
                .userId(p.getUserId())
                .title(p.getTitle())
                .content(summarize(p.getContent(), 140))
                .likeCount(p.getLikeCount())
                .commentCount((int) mentRepository.countByPost(p))
                .categoryKey(p.getCategory() != null ? p.getCategory().getCategoryKey() : null)
                .categoryName(p.getCategory() != null ? p.getCategory().getName() : null)
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();
    }

    /**
     * Ment -> MyCommentRes 변환
     */
    private MyCommentRes toMyCommentRes(Ment m) {
        CommunityPost post = m.getPost();
        return MyCommentRes.builder()
                .commentId(m.getCommentId())
                .content(m.getContent())
                .createdAt(m.getCreatedAt())
                .postId(post.getPostId())
                .postTitle(post.getTitle())
                .postAuthorId(post.getUserId())
                .build();
    }

    /**
     * 본문 요약 (목록용)
     */
    private String summarize(String text, int max) {
        if (text == null) return "";
        text = text.strip();
        return text.length() <= max ? text : text.substring(0, max) + "...";
    }
}