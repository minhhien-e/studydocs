package studydocs.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import studydocs.response.UploadResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final AmazonS3 amazonS3;

    @Value("${cloudflare.r2.bucket-name}")
    private String bucketName;

    @Value("${cloudflare.r2.public-domain}")
    private String r2PublicDomain;

    public UploadResponse uploadFile(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        metadata.setContentType(file.getContentType());

        amazonS3.putObject(bucketName, filename, file.getInputStream(), metadata);

        // Đường dẫn public đúng: https://{r2PublicDomain}/{filename}
        String publicUrl = String.format("https://%s/%s", r2PublicDomain, filename);
        return new UploadResponse(filename, publicUrl);
    }

    public UploadResponse uploadFile(File file) throws IOException {
        String filename = UUID.randomUUID() + "_" + file.getName();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.length());
        String contentType = Files.probeContentType(file.toPath());
        metadata.setContentType(contentType != null ? contentType : "application/octet-stream");

        try (FileInputStream fis = new FileInputStream(file)) {
            amazonS3.putObject(bucketName, filename, fis, metadata);
        }

        // Đường dẫn public đúng
        String publicUrl = String.format("https://%s/%s", r2PublicDomain, filename);
        return new UploadResponse(filename, publicUrl);
    }
}