package com.studydocs.infras.storage;

import com.studydocs.shared.exception.AppException;
import com.studydocs.shared.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class LocalFileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    public LocalFileStorageServiceImpl(@Value("${file.upload-dir:uploads}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            log.error("Could not create upload directory", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file, String subFolder) {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (originalFileName.contains("..")) {
                throw new AppException(ErrorCode.INVALID_REQUEST, "Filename contains invalid path sequence " + originalFileName);
            }

            String extension = "";
            int i = originalFileName.lastIndexOf('.');
            if (i > 0) {
                extension = originalFileName.substring(i);
            }
            String newFileName = UUID.randomUUID().toString() + extension;

            Path targetLocation = this.fileStorageLocation;
            if (subFolder != null && !subFolder.isBlank()) {
                targetLocation = targetLocation.resolve(subFolder);
                Files.createDirectories(targetLocation);
            }

            Path filePath = targetLocation.resolve(newFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return (subFolder != null && !subFolder.isBlank() ? subFolder + "/" : "") + newFileName;
        } catch (IOException ex) {
            log.error("Could not store file {}", originalFileName, ex);
            throw new AppException(ErrorCode.FILE_UPLOAD_FAILED, "Could not store file " + originalFileName);
        }
    }

    @Override
    public byte[] loadFileAsBytes(String filePath) {
        try {
            Path path = this.fileStorageLocation.resolve(filePath).normalize();
            return Files.readAllBytes(path);
        } catch (IOException ex) {
            throw new AppException(ErrorCode.RESOURCE_NOT_FOUND, "File not found: " + filePath);
        }
    }

    @Override
    public void deleteFile(String filePath) {
        try {
            Path path = this.fileStorageLocation.resolve(filePath).normalize();
            Files.deleteIfExists(path);
        } catch (IOException ex) {
            log.warn("Could not delete file {}", filePath, ex);
        }
    }
}
