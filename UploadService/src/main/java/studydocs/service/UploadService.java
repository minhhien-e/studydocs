package studydocs.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import studydocs.exception.FileSizeExceededException;
import studydocs.exception.FileTypeNotAllowedException;
import studydocs.response.UploadResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final AmazonS3 amazonS3;

    @Value("${cloudflare.r2.bucket-name}")
    private String bucketName;

    @Value("${cloudflare.r2.public-domain}")
    private String r2PublicDomain;

    @Value("${cloudflare.r2.allowed-file-types}")
    private String allowedFileTypesStr;

    @Value("${cloudflare.r2.max-file-size}")
    private long maxFileSize;

    private final Set<String> allowedFileTypes = new HashSet<>(Arrays.asList(allowedFileTypesStr.split(",")));

    public UploadResponse uploadFile(MultipartFile file) throws IOException {
        // Kiểm tra file rỗng
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File rỗng, vui lòng chọn file hợp lệ");
        }

        // Kiểm tra định dạng file
        String contentType = file.getContentType();
        if (!allowedFileTypes.contains(contentType)) {
            throw new FileTypeNotAllowedException("Định dạng file không được phép: " + contentType);
        }

        // Kiểm tra kích thước file
        long fileSize = file.getSize();
        if (fileSize > maxFileSize) {
            throw new FileSizeExceededException("Kích thước file vượt quá giới hạn 50 MB");
        }

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileSize);
        metadata.setContentType(contentType);

        amazonS3.putObject(bucketName, filename, file.getInputStream(), metadata);

        String publicUrl = String.format("https://%s/%s", r2PublicDomain, filename);
        return new UploadResponse(filename, publicUrl);
    }

    public UploadResponse uploadFile(File file) throws IOException {
        // Kiểm tra định dạng file
        String contentType = Files.probeContentType(file.toPath());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        if (!allowedFileTypes.contains(contentType)) {
            throw new FileTypeNotAllowedException("Định dạng file không được phép: " + contentType);
        }

        // Kiểm tra kích thước file
        long fileSize = file.length();
        if (fileSize > maxFileSize) {
            throw new FileSizeExceededException("Kích thước file vượt quá giới hạn 50 MB");
        }

        String filename = UUID.randomUUID() + "_" + file.getName();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileSize);
        metadata.setContentType(contentType);

        try (FileInputStream fis = new FileInputStream(file)) {
            amazonS3.putObject(bucketName, filename, fis, metadata);
        }

        String publicUrl = String.format("https://%s/%s", r2PublicDomain, filename);
        return new UploadResponse(filename, publicUrl);
    }
}