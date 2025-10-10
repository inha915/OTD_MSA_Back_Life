package com.otd.otd_msa_back_life.community.service;

import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import com.otd.otd_msa_back_life.community.entity.Ment;
import com.otd.otd_msa_back_life.community.repository.CommunityPostRepository;
import com.otd.otd_msa_back_life.community.repository.MentRepository;
import com.otd.otd_msa_back_life.community.web.dto.ment.MentCreateReq;
import com.otd.otd_msa_back_life.community.web.dto.ment.MentRes;
import com.otd.otd_msa_back_life.configuration.model.UserPrincipal;
import com.otd.otd_msa_back_life.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentService {

    private final MentRepository mentRepository;
    private final CommunityPostRepository postRepository;
    private final NotificationService notificationService;

    @Transactional
    public MentRes create(Long postId, Long requesterId, MentCreateReq req) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));

        // 현재 로그인 사용자 닉네임 (헤더/필터에서 세팅된 JwtUser의 nickName 사용)
        String writerName = "회원";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserPrincipal up
                && up.getJwtUser().getNickName() != null && !up.getJwtUser().getNickName().isBlank()) {
            writerName = up.getJwtUser().getNickName();
        }

        // ✅ 닉네임을 함께 저장(denormalize)
        Ment ment = Ment.builder()
                .post(post)
                .userId(requesterId)
                .content(req.getContent())
                .authorNickname(writerName)  // ← 엔티티에 String authorNickname 필드 필요
                .build();

        Ment saved = mentRepository.save(ment);

        // 내 글이 아닐 때만 알림
        if (!post.getUserId().equals(requesterId)) {
            String body = writerName + " 님이 댓글을 남겼습니다. " + truncateContent(req.getContent());
            notificationService.create("커뮤니티", body, "comment", post.getUserId());
        }

        // 작성 직후 응답 (닉네임 포함)
        return MentRes.builder()
                .commentId(saved.getCommentId())
                .postId(post.getPostId())
                .userId(saved.getUserId())
                .content(saved.getContent())
                .createdAt(saved.getCreatedAt())
                .authorNickname(saved.getAuthorNickname()) // ✅ 저장된 닉네임 그대로 반환
                .build();
    }

    @Transactional(readOnly = true)
    public List<MentRes> listByPost(Long postId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));

        // 현재 로그인 사용자 정보 (내가 쓴 댓글 fallback용)
        Long currentUserId = null;
        String currentNick = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserPrincipal up) {
            currentUserId = up.getJwtUser().getSignedUserId();
            currentNick = up.getJwtUser().getNickName();
        }
        final Long me = currentUserId;
        final String meNick = (currentNick == null || currentNick.isBlank()) ? "회원" : currentNick;

        // ✅ 저장된 authorNickname을 우선 사용 (없으면 내 댓글만 내 닉네임 fallback)
        return mentRepository.findByPostOrderByCreatedAtAsc(post)
                .stream()
                .map(m -> MentRes.builder()
                        .commentId(m.getCommentId())
                        .postId(post.getPostId())
                        .userId(m.getUserId())
                        .content(m.getContent())
                        .createdAt(m.getCreatedAt())
                        .authorNickname(
                                m.getAuthorNickname() != null && !m.getAuthorNickname().isBlank()
                                        ? m.getAuthorNickname()
                                        : (me != null && me.equals(m.getUserId()) ? meNick : "익명")
                        )
                        .build())
                .toList();
    }

    @Transactional
    public void delete(Long mentId, Long requesterId) {
        Ment ment = mentRepository.findById(mentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음: " + mentId));
        if (!ment.getUserId().equals(requesterId)) {
            throw new IllegalStateException("삭제 권한 없음");
        }
        mentRepository.delete(ment);
    }

    private String truncateContent(String content) {
        if (content == null) return "";
        String clean = content.trim().replaceAll("\\s+", " ");
        return clean.length() > 40 ? clean.substring(0, 40) + "…" : clean;
    }
}
