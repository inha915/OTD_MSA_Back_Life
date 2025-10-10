// src/main/java/com/otd/otd_msa_back_life/community/web/dto/file/PostFileRes.java
package com.otd.otd_msa_back_life.community.web.dto.file;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostFileRes {
    private Long fileId;
    private Long postId;
    private Long userId;
    private String fileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    private Integer width;
    private Integer height;
    private Integer orderIdx;
    private LocalDateTime createdAt;
}
