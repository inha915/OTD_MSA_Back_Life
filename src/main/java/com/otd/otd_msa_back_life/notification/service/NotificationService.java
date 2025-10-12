package com.otd.otd_msa_back_life.notification.service;

import com.otd.otd_msa_back_life.notification.entity.Notification;
import com.otd.otd_msa_back_life.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repo;

    @Transactional(readOnly = true)
    public List<Notification> list(Long userId) {
        return repo.findTop100ByUserIdOrderByCreatedAtDesc(userId);
    }

    @Transactional
    public void markRead(Long userId, Long id) {
        repo.findById(id).ifPresent(n -> {
            if (n.getUserId().equals(userId) && !n.isRead()) {
                n.setRead(true);
            }
        });
    }

    @Transactional
    public int markAllRead(Long userId) {
        List<Notification> list = repo.findTop100ByUserIdOrderByCreatedAtDesc(userId);
        int cnt = 0;
        for (Notification n : list) {
            if (!n.isRead()) {
                n.setRead(true);
                cnt++;
            }
        }
        return cnt;
    }
}
