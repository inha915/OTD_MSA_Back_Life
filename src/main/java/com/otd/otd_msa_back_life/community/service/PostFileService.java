package com.otd.otd_msa_back_life.community.service;

import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import com.otd.otd_msa_back_life.community.entity.CommunityPostFile;
import com.otd.otd_msa_back_life.community.repository.CommunityPostFileRepository;
import com.otd.otd_msa_back_life.community.repository.CommunityPostRepository;
import com.otd.otd_msa_back_life.community.service.storage.FileStorageService;
import com.otd.otd_msa_back_life.community.web.dto.file.PostFileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostFileService {

    private final CommunityPostRepository postRepository;
    private final CommunityPostFileRepository fileRepository;
    private final FileStorageService storageService;

    @Transactional
    public List<PostFileRes> upload(Long postId, Long memberNoLogin, MultipartFile[] files) throws IOException {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));

        return java.util.Arrays.stream(files)
                .filter(f -> !f.isEmpty())
                .map(f -> saveOne(post, memberNoLogin, f))
                .toList();
    }

    private PostFileRes saveOne(CommunityPost post, Long memberNoLogin, MultipartFile f) {
        try {
            String savedPath = storageService.save(f);
            String fileType = f.getContentType();

            CommunityPostFile entity = CommunityPostFile.builder()
                    .post(post)
                    .userId(memberNoLogin)
                    .fileName(f.getOriginalFilename())
                    .filePath(savedPath)
                    .fileType(fileType)
                    .uploadedAt(LocalDateTime.now())
                    .build();

            CommunityPostFile saved = fileRepository.save(entity);

            return PostFileRes.builder()
                    .fileId(saved.getId())
                    .postId(post.getPostId())
                    .userId(memberNoLogin)
                    .fileName(saved.getFileName())
                    .filePath(saved.getFilePath())
                    .fileType(saved.getFileType())
                    .uploadedAt(saved.getUploadedAt())
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + f.getOriginalFilename(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<PostFileRes> listByPost(Long postId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + postId));

        return fileRepository.findByPost(post).stream()
                .map(saved -> PostFileRes.builder()
                        .fileId(saved.getId())
                        .postId(post.getPostId())
                        .userId(saved.getUserId())
                        .fileName(saved.getFileName())
                        .filePath(saved.getFilePath())
                        .fileType(saved.getFileType())
                        .uploadedAt(saved.getUploadedAt())
                        .build())
                .toList();
    }

    @Transactional
    public void delete(Long fileId, Long requesterId) {
        CommunityPostFile file = fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("파일 없음: " + fileId));

        // 업로더만 삭제 가능 (필요 시 권한 정책 강화)
        if (!file.getUserId().equals(requesterId)) {
            throw new IllegalStateException("삭제 권한 없음");
        }

        try {
            storageService.delete(file.getFilePath());
        } catch (Exception ignored) {
            // 물리 파일이 없더라도 DB는 삭제
        }
        fileRepository.delete(file);
    }
}
