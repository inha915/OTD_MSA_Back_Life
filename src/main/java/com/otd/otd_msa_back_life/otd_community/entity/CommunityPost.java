package com.otd.otd_msa_back_life.otd_community.entity;

import com.otd.otd_msa_back_life.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "community_post")
public class CommunityPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    // MSA 환경에서는 외래키 대신 단순히 memberId 저장
    @Column(name = "member_no_login", nullable = false)
    private Long memberNoLogin;

    @Column(length = 200, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount = 0;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // ✅ 같은 서비스 내 연관관계만 유지
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ment> ments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityPostFile> files = new ArrayList<>();
}
