package com.otd.otd_msa_back_life.notification.controller;

import com.otd.otd_msa_back_life.notification.entity.Notification;
import com.otd.otd_msa_back_life.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    private Long resolveUserId(@RequestHeader(value = "X-User-Id", required = false) String uid1,
                               @RequestHeader(value = "X-MEMBER-ID", required = false) String uid2) {
        String raw = (uid1 != null && !uid1.isBlank()) ? uid1 : uid2;
        if (raw == null || raw.isBlank()) throw new IllegalArgumentException("Missing user id header");
        return Long.parseLong(raw);
    }

    @GetMapping
    public ResponseEntity<List<Notification>> list(@RequestHeader(value = "X-User-Id", required = false) String uid1,
                                                   @RequestHeader(value = "X-MEMBER-ID", required = false) String uid2) {
        Long userId = resolveUserId(uid1, uid2);
        return ResponseEntity.ok(service.list(userId));
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> markRead(@PathVariable Long id,
                                         @RequestHeader(value = "X-User-Id", required = false) String uid1,
                                         @RequestHeader(value = "X-MEMBER-ID", required = false) String uid2) {
        Long userId = resolveUserId(uid1, uid2);
        service.markRead(userId, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/mark-all-read")
    public ResponseEntity<Integer> markAllRead(@RequestHeader(value = "X-User-Id", required = false) String uid1,
                                               @RequestHeader(value = "X-MEMBER-ID", required = false) String uid2) {
        Long userId = resolveUserId(uid1, uid2);
        int cnt = service.markAllRead(userId);
        return ResponseEntity.ok(cnt);
    }
}
