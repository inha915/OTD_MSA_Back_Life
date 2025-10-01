package com.otd.otd_msa_back_life.community.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(
        name = "community_category",
        uniqueConstraints = @UniqueConstraint(name = "uq_category_key", columnNames = "category_key")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CommunityCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long categoryId;

    /** ex) free, diet, work, love */
    @NaturalId(mutable = false) // 선택: 하이버네이트 NaturalId 최적화
    @Column(name = "category_key", length = 50, nullable = false, updatable = false)
    private String categoryKey;

    /** UI 라벨(예: 자유수다) */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /** 입력값 정규화(선택): key는 소문자/trim, name은 trim */
    @PrePersist
    @PreUpdate
    private void normalize() {
        if (categoryKey != null) categoryKey = categoryKey.trim().toLowerCase();
        if (name != null) name = name.trim();
    }
}
