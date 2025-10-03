// src/main/java/com/otd/otd_msa_back_life/community/service/storage/FileStorageService.java
package com.otd.otd_msa_back_life.community.service.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    String save(MultipartFile file) throws IOException;

    boolean delete(String filePath) throws IOException;

    StoredFileMeta saveWithMeta(MultipartFile file) throws IOException;

    record StoredFileMeta(
            String filePath,
            String contentType,
            long size,
            Integer width,
            Integer height
    ) {}
}
