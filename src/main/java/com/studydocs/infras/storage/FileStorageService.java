package com.studydocs.infras.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file, String subFolder);
    byte[] loadFileAsBytes(String filePath);
    void deleteFile(String filePath);
}
