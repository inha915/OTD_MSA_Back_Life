package com.otd.otd_msa_back_life.notification.service;

import com.otd.otd_msa_back_life.notification.dto.NotificationRes;
import com.otd.otd_msa_back_life.notification.entity.Notification;
import com.otd.otd_msa_back_life.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    // 회원별 알림 목록
    public List<NotificationRes> getNotifications(Long userId) {
        return notificationRepository.findAllByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(NotificationRes::fromEntity)
                .toList();
    }

    // 단일 알림 읽음 처리
    @Transactional
    public void markRead(Long id) {
        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        n.setRead(true);
    }

    // 전체 읽음 처리
    @Transactional
    public void markAllRead(Long userId) {
        List<Notification> list = notificationRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
        list.forEach(n -> n.setRead(true));
    }

    // 새 알림 생성 (커뮤니티 댓글 등)
    public Notification create(String title, String body, String category, Long userId) {
        Notification n = Notification.builder()
                .title(title)
                .body(body)
                .category(category)
                .userId(userId)
                .build();
        return notificationRepository.save(n);
    }
}
