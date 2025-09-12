package com.otd.otd_msa_back_life.otd_community.service;

import com.otd.otd_msa_back_life.otd_community.entity.CommunityPost;
import com.otd.otd_msa_back_life.otd_community.entity.Ment;
import com.otd.otd_msa_back_life.otd_community.repository.CommunityPostRepository;
import com.otd.otd_msa_back_life.otd_community.repository.MentRepository;
import com.otd.otd_msa_back_life.otd_community.web.dto.ment.MentCreateReq;
import com.otd.otd_msa_back_life.otd_community.web.dto.ment.MentRes;
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
                .memberNoLogin(req.getMemberNoLogin())
                .content(req.getContent())
                .build();

        Ment saved = mentRepository.save(ment);
        return MentRes.builder()
                .mentId(saved.getCommentId())
                .postId(post.getPostId())
                .memberNoLogin(saved.getMemberNoLogin())
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
                        .memberNoLogin(m.getMemberNoLogin())
                        .content(m.getContent())
                        .createdAt(m.getCreatedAt())
                        .build())
                .toList();
    }

    @Transactional
    public void delete(Long mentId, Long requesterId) {
        Ment ment = mentRepository.findById(mentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음: " + mentId));
        if (!ment.getMemberNoLogin().equals(requesterId)) {
            throw new IllegalStateException("삭제 권한 없음");
        }
        mentRepository.delete(ment);
    }
}
