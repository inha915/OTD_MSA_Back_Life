package com.otd.otd_msa_back_life.notification.dto;

import com.otd.otd_msa_back_life.notification.entity.Notification;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRes {
    private Long id;
    private String title;
    private String body;
    private String category;
    private boolean read;
    private LocalDateTime createdAt;

    public static NotificationRes fromEntity(Notification n) {
        return NotificationRes.builder()
                .id(n.getId())
                .title(n.getTitle())
                .body(n.getBody())
                .category(n.getCategory())
                .read(n.isRead())
                .createdAt(n.getCreatedAt())
                .build();
    }
}
