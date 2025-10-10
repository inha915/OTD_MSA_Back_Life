// src/main/java/com/otd/otd_msa_back_life/notification/repository/NotificationRepository.java
package com.otd.otd_msa_back_life.notification.repository;

import com.otd.otd_msa_back_life.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findTop100ByUserIdOrderByCreatedAtDesc(Long userId);
    long countByUserIdAndReadFalse(Long userId);
}
