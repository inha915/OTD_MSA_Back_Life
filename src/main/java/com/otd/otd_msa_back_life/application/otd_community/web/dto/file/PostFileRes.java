package com.otd.otd_msa_back_life.application.otd_community.web.dto.file;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostFileRes {
    private Long fileId;
    private Long postId;
    private Long memberNoLogin;
    private String fileName;     // 원본 파일명
    private String filePath;     // 저장된 경로(상대/절대)
    private String fileType;     // MIME or 확장자
    private LocalDateTime uploadedAt;
}
