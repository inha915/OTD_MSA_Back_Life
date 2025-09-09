package com.otd.otd_msa_back_life.otd_community.service.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalFileStorageService implements FileStorageService {

    // application.yml: constants.file.directory: D:\inha\healthcare\img
    @Value("${constants.file.directory}")
    private String rootDir;

    @Override
    public String save(MultipartFile file) throws IOException {
        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String safeName = UUID.randomUUID() + (ext != null ? "." + ext : "");
        Path dir = Paths.get(rootDir).toAbsolutePath().normalize();
        Files.createDirectories(dir);
        Path target = dir.resolve(safeName);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return target.toString(); // 저장 절대경로 반환
    }

    @Override
    public boolean delete(String filePath) throws IOException {
        if (filePath == null) return false;
        Path p = Paths.get(filePath);
        return Files.deleteIfExists(p);
    }
}
