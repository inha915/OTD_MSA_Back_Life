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
@RequestMapping("/api/OTD/community")
public class PostFileController {

    private final PostFileService postFileService;

    @GetMapping("/posts/{postId}/files")
    public List<PostFileRes> list(@PathVariable Long postId) {
        System.out.println("[PostFileController] LIST postId=" + postId);
        return postFileService.listByPost(postId);
    }

    @GetMapping("/files")
    public List<PostFileRes> listByQuery(@RequestParam("postId") Long postId) {
        System.out.println("[PostFileController] LIST (query) postId=" + postId);
        return postFileService.listByPost(postId);
    }

    @PostMapping(value = "/posts/{postId}/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<PostFileRes> upload(@PathVariable Long postId,
                                    @RequestHeader("X-MEMBER-ID") Long memberNoLogin,
                                    @RequestPart("files") MultipartFile[] files) throws Exception {
        System.out.println("[PostFileController] HIT postId=" + postId
                + ", member=" + memberNoLogin
                + ", files=" + (files == null ? 0 : files.length));
        return postFileService.upload(postId, memberNoLogin, files);
    }

    @DeleteMapping("/files/{fileId}")
    public void delete(@PathVariable Long fileId,
                       @RequestHeader("X-MEMBER-ID") Long requesterId) {
        System.out.println("[PostFileController] DELETE fileId=" + fileId + ", requester=" + requesterId);
        postFileService.delete(fileId, requesterId);
    }
}
