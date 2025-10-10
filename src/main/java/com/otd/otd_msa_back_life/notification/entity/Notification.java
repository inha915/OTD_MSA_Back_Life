package com.otd.otd_msa_back_life.notification.entity;

import com.otd.otd_msa_back_life.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 알림 제목 (예: "커뮤니티", "업데이트 안내")
    private String title;

    // 알림 내용
    @Column(columnDefinition = "TEXT")
    private String body;

    // 알림 카테고리 (notice / comment / like 등)
    private String category;

    // 수신자 (회원 ID)
    @Column(name = "user_id")
    private Long userId;

    // 읽음 여부
    @Column(name = "is_read", nullable = false)
    private boolean read = false;
}
