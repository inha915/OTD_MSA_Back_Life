package com.otd.otd_msa_back_life.notification.controller;

import com.otd.otd_msa_back_life.notification.dto.NotificationRes;
import com.otd.otd_msa_back_life.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    // 알림 전체 조회
    @GetMapping("/{userId}")
    public List<NotificationRes> getNotifications(@PathVariable Long userId) {
        return notificationService.getNotifications(userId);
    }

    // 개별 알림 읽음 처리
    @PostMapping("/read/{id}")
    public void markRead(@PathVariable Long id) {
        notificationService.markRead(id);
    }

    // 전체 읽음 처리
    @PostMapping("/read-all/{userId}")
    public void markAllRead(@PathVariable Long userId) {
        notificationService.markAllRead(userId);
    }

    // 새 알림 생성 (테스트용)
    @PostMapping
    public NotificationRes createNotification(
            @RequestParam String title,
            @RequestParam String body,
            @RequestParam String category,
            @RequestParam Long userId
    ) {
        return NotificationRes.fromEntity(
                notificationService.create(title, body, category, userId)
        );
    }
}
