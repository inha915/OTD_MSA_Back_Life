package com.otd.otd_msa_back_life.community.service;

import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import com.otd.otd_msa_back_life.community.entity.CommunityPostFile;
import com.otd.otd_msa_back_life.community.repository.CommunityPostFileRepository;
import com.otd.otd_msa_back_life.community.repository.CommunityPostRepository;
import com.otd.otd_msa_back_life.community.service.storage.FileStorageService;
import com.otd.otd_msa_back_life.community.web.dto.file.PostFileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostFileService {

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final int MAX_FILES_PER_REQ = 10;

    private final CommunityPostRepository postRepository;
    private final CommunityPostFileRepository fileRepository;
    private final FileStorageService storageService;

    @Transactional
    public List<PostFileRes> upload(Long postId, Long memberNoLogin, MultipartFile[] files) throws IOException {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글 없음: " + postId));

        Long ownerId = post.getUserId();
        if (ownerId == null || !ownerId.equals(memberNoLogin)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "업로드 권한 없음");
        }

        List<MultipartFile> valid = Arrays.stream(files == null ? new MultipartFile[0] : files)
                .filter(f -> f != null && !f.isEmpty())
                .toList();

        if (valid.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "업로드 대상 파일 없음");
        if (valid.size() > MAX_FILES_PER_REQ)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일 개수 초과");

        for (MultipartFile f : valid) {
            if (f.getSize() > MAX_FILE_SIZE)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일 용량 초과");
            String ct = f.getContentType();
            if (ct == null || !ct.startsWith("image/"))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미지 파일만 허용");

            FileStorageService.StoredFileMeta meta = storageService.saveWithMeta(f);

            CommunityPostFile saved = fileRepository.save(
                    CommunityPostFile.builder()
                            .post(post)
                            .userId(memberNoLogin)
                            .fileName(f.getOriginalFilename())
                            .filePath(meta.filePath())          // 반드시 /static/community/.. URL
                            .fileType(normContentType(ct))
                            .build()
            );

            // 필요시 로그
            System.out.println("[PostFileService] saved fileId=" + saved.getId() + ", path=" + saved.getFilePath());
        }

        // 업로드 후 최신 목록 반환
        return listByPost(postId);
    }

    @Transactional(readOnly = true)
    public List<PostFileRes> listByPost(Long postId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글 없음: " + postId));

        return fileRepository.findByPost(post).stream()
                .map(saved -> PostFileRes.builder()
                        .fileId(saved.getId())
                        .postId(post.getPostId())
                        .userId(saved.getUserId())
                        .fileName(saved.getFileName())
                        .filePath(saved.getFilePath())
                        .fileType(saved.getFileType())
                        .build())
                .toList();
    }

    @Transactional
    public void delete(Long fileId, Long requesterId) {
        CommunityPostFile file = fileRepository.findById(fileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "파일 없음: " + fileId));

        if (!file.getUserId().equals(requesterId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 권한 없음");
        }

        try { storageService.delete(file.getFilePath()); } catch (Exception ignore) {}
        fileRepository.delete(file);
    }

    private String normContentType(String ct) {
        if (ct == null) return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        return ct.trim().toLowerCase();
    }
}
