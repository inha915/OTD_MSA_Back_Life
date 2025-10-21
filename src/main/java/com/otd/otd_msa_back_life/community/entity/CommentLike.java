package com.otd.otd_msa_back_life.community.entity;

import com.otd.otd_msa_back_life.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "comment_like",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_comment_user", columnNames = {"comment_id", "user_id"})
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 댓글과의 관계 (FK 연결)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "comment_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_comment_like_comment")
    )
    private Ment comment;

    // user_id는 FK 없이 숫자만 저장
    @Column(name = "user_id", nullable = false)
    private Long userId;
}
