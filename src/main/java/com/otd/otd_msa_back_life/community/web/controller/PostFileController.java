package com.otd.otd_msa_back_life.community.web.controller;

import com.otd.otd_msa_back_life.community.service.PostFileService;
import com.otd.otd_msa_back_life.community.web.dto.file.PostFileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community")
public class PostFileController {

    private final PostFileService postFileService;

    /**
     * 게시글별 파일 목록
     */
    @GetMapping("/posts/{postId}/files")
    public List<PostFileRes> list(@PathVariable Long postId) {
        return postFileService.listByPost(postId);
    }

    /**
     * 파일 업로드 (여러 개)
     * form-data:
     *   - files: (file[]), 여러개
     *   - X-MEMBER-ID: 업로더 ID (게이트웨이에서 전달)
     */
    @PostMapping(value = "/posts/{postId}/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<PostFileRes> upload(@PathVariable Long postId,
                                    @RequestHeader("X-MEMBER-ID") Long memberNoLogin,
                                    @RequestPart("files") MultipartFile[] files) {
        try {
            return postFileService.upload(postId, memberNoLogin, files);
        } catch (Exception e) {
            throw new RuntimeException("업로드 실패", e);
        }
    }

    /**
     * 파일 삭제
     */
    @DeleteMapping("/files/{fileId}")
    public void delete(@PathVariable Long fileId,
                       @RequestHeader("X-MEMBER-ID") Long requesterId) {
        postFileService.delete(fileId, requesterId);
    }
}
