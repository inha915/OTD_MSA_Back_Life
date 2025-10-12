package com.otd.otd_msa_back_life.community.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    /**
     * 서버 저장 루트: /home/green/download
     */
    private String uploadDirectory;

    /**
     * 프로필 하위 폴더명: profile
     * (커뮤니티는 고정 "community" 사용)
     */
    private String profilePic;
}
