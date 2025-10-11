package com.otd.otd_msa_back_life.community.service.storage;

import com.otd.otd_msa_back_life.community.config.FileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(prefix = "file", name = "upload-directory", matchIfMissing = false)
public class LocalFileStorageService implements FileStorageService {

    private final FileProperties props;

    // 정적 매핑 경로(StaticResourceConfig와 일치)
    private static final String URL_PREFIX = "/static/community/";

    @Override
    public String save(MultipartFile file) throws IOException {
        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String safeName = UUID.randomUUID() + (ext != null && !ext.isBlank() ? "." + ext : "");

        // 실제 저장 경로: [uploadDir]/community
        Path dir = Paths.get(props.getUploadDirectory(), "community").toAbsolutePath().normalize();
        Files.createDirectories(dir);

        Path target = dir.resolve(safeName);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        // ⬇ 저장 경로와 반환 URL 로그
        System.out.println("[StaticSave] to=" + target.toAbsolutePath()
                + " -> url=" + URL_PREFIX + safeName);

        // 브라우저 접근 URL 반환
        return URL_PREFIX + safeName;
    }

    @Override
    public boolean delete(String filePathOrUrl) throws IOException {
        if (filePathOrUrl == null || filePathOrUrl.isBlank()) return false;
        Path real = toRealPath(filePathOrUrl);
        boolean deleted = Files.deleteIfExists(real);
        System.out.println("[StaticDelete] target=" + real + ", deleted=" + deleted);
        return deleted;
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

        System.out.println("[StaticSaveMeta] url=" + url + ", ct=" + ct + ", size=" + size
                + ", w=" + width + ", h=" + height);

        return new StoredFileMeta(url, ct, size, width, height);
    }

    private Path toRealPath(String filePathOrUrl) {
        // "/static/community/xxx" -> [uploadDir]/community/xxx
        if (filePathOrUrl.startsWith(URL_PREFIX)) {
            String fileName = filePathOrUrl.substring(URL_PREFIX.length());
            return Paths.get(props.getUploadDirectory(), "community")
                    .toAbsolutePath().normalize().resolve(fileName);
        }
        // 절대/상대 물리 경로로 들어온 경우
        return Paths.get(filePathOrUrl).toAbsolutePath().normalize();
    }
}
