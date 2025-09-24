package com.otd.otd_msa_back_life.community.service;

import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import com.otd.otd_msa_back_life.community.entity.Ment;
import com.otd.otd_msa_back_life.community.repository.CommunityPostRepository;
import com.otd.otd_msa_back_life.community.repository.MentRepository;
import com.otd.otd_msa_back_life.community.web.dto.ment.MentCreateReq;
import com.otd.otd_msa_back_life.community.web.dto.ment.MentRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentService {

    private final MentRepository mentRepository;
    private final CommunityPostRepository postRepository;

    @Transactional
    public MentRes create(Long postId, MentCreateReq req) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));

        Ment ment = Ment.builder()
                .post(post)
                .userId(req.getUserId())   // ✅ userId로 변경
                .content(req.getContent())
                .build();

        Ment saved = mentRepository.save(ment);
        return MentRes.builder()
                .mentId(saved.getCommentId())
                .postId(post.getPostId())
                .userId(saved.getUserId()) // ✅ userId로 변경
                .content(saved.getContent())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public List<MentRes> listByPost(Long postId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));
        return mentRepository.findByPostOrderByCreatedAtAsc(post)
                .stream()
                .map(m -> MentRes.builder()
                        .mentId(m.getCommentId())
                        .postId(post.getPostId())
                        .userId(m.getUserId())   // ✅ userId로 변경
                        .content(m.getContent())
                        .createdAt(m.getCreatedAt())
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
}
