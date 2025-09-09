package com.otd.otd_msa_back_life.otd_community.service.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    /**
     * 파일 하나 저장 후 저장된 상대/절대 경로 반환
     */
    String save(MultipartFile file) throws IOException;

    /**
     * 물리 파일 삭제 (성공/실패 여부)
     */
    boolean delete(String filePath) throws IOException;
}
