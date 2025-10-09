package com.otd.otd_msa_back_life.community.service.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalFileStorageService implements FileStorageService {

    // 예) application.yml
    // constants:
    //   file:
    //     directory: D:\inha\healthcare\img\item
    @Value("${constants.file.directory}")
    private String rootDir;

    // StaticResourceConfig의 handler와 반드시 일치해야 함
    private static final String URL_PREFIX = "/static/community/";

    @Override
    public String save(MultipartFile file) throws IOException {
        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String safeName = UUID.randomUUID() + (ext != null && !ext.isBlank() ? "." + ext : "");
        Path dir = Paths.get(rootDir).toAbsolutePath().normalize();
        Files.createDirectories(dir);
        Path target = dir.resolve(safeName);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return URL_PREFIX + safeName; // 정적 URL 반환
    }

    @Override
    public boolean delete(String filePathOrUrl) throws IOException {
        if (filePathOrUrl == null || filePathOrUrl.isBlank()) return false;
        Path real = toRealPath(filePathOrUrl);
        return Files.deleteIfExists(real);
    }

    @Override
    public StoredFileMeta saveWithMeta(MultipartFile file) throws IOException {
        String url = save(file);
        String ct = file.getContentType();
        long size = file.getSize();
        Integer width = null, height = null;

        try {
            BufferedImage img = ImageIO.read(file.getInputStream());
            if (img != null) { width = img.getWidth(); height = img.getHeight(); }
        } catch (Exception ignore) {}

        return new StoredFileMeta(url, ct, size, width, height);
    }

    private Path toRealPath(String filePathOrUrl) {
        if (filePathOrUrl.startsWith(URL_PREFIX)) {
            String fileName = filePathOrUrl.substring(URL_PREFIX.length());
            return Paths.get(rootDir).toAbsolutePath().normalize().resolve(fileName);
        }
        return Paths.get(filePathOrUrl).toAbsolutePath().normalize();
    }
}
