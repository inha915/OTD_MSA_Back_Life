package com.otd.otd_msa_back_life.community.entity;

import com.otd.otd_msa_back_life.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "community_like",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_like_post_user",
                columnNames = {"post_id", "user_id"}
        )
)
public class CommunityLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "post_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_like_post")
    )
    private CommunityPost post;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
