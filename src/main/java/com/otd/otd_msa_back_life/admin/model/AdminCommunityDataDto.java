package com.otd.otd_msa_back_life.admin.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminCommunityDataDto {
    private String nickName;
    private String title;
    private String category;
    private String content;
    private int likeCount;
    private boolean isDeleted;

    private List<CommentDto> comments;
    private List<FileDto> files;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentDto {
        private Long id;
        private String content;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileDto {
        private Long id;
        private String filePath;
    }
}

