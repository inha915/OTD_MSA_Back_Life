package com.otd.otd_msa_back_life.configuration.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 프로필 이미지 경로를 정규화해서
 * 프론트에서 깨지지 않도록 절대경로 또는 기본이미지만 반환
 */
@Component
public class ProfileUrlNormalizer {

    // application.yml에 등록해둘 base url (기본값은 로컬 8080)
    @Value("${app.public-base-url:http://localhost:8080}")
    private String publicBaseUrl;

    private static final String DEFAULT = "/otd/image/main/default-profile.png";

    /**
     * DB에 저장된 raw 경로를 프론트에서 바로 쓸 수 있는 형태로 변환
     */
    public String normalize(String raw) {
        if (raw == null || raw.isBlank()) {
            return DEFAULT;
        }

        String v = raw.trim();

        // 1) 절대 URL이면 그대로 사용
        if (v.startsWith("http://") || v.startsWith("https://")) {
            return v;
        }

        // 2) 기본 이미지 경로면 그대로 사용
        if (v.startsWith("/otd/image/main/")) {
            return v;
        }

        // 3) 상대경로(profile1.jpg 등)는 기본 이미지로 대체
        return DEFAULT;

        // 만약 상대경로도 쓰고 싶다면 ↓ 이렇게 수정 가능
        // return publicBaseUrl + (v.startsWith("/") ? v : "/" + v);
    }
}
