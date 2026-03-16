package studydocs.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import studydocs.client.RemoteApiCaller;
import studydocs.domain.Document;
import studydocs.dto.projection.FileProjection;
import studydocs.dto.request.UploadDocumentRequest;
import studydocs.dto.response.ApiResponse;
import studydocs.exception.DocumentNotFoundException;
import studydocs.exception.DocumentProcessingException;
import studydocs.repository.DocumentRepository;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final RemoteApiCaller remoteApiCaller;

    @Value("${upload.service.url}")
    private String uploadServiceUrl;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    @Transactional
    public Document createAndUploadDocument(UploadDocumentRequest req, MultipartFile file) {
        try {
            Document document = new Document(req.getUserId(), req.getTitle(), req.getDescription());
            document.markUploading();
            document = documentRepository.save(document);

            UUID documentId = document.getId();
            log.info("DocumentService: Tạo document id={} và đặt trạng thái UPLOADING", documentId);

            // Upload to external service
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            // Wrap the file to ensure filename is preserved
            org.springframework.core.io.Resource fileResource = new org.springframework.core.io.ByteArrayResource(
                    file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
            HttpHeaders fileHeaders = new HttpHeaders();
            fileHeaders.setContentType(MediaType.parseMediaType(file.getContentType()));
            HttpEntity<org.springframework.core.io.Resource> filePart = new HttpEntity<>(fileResource, fileHeaders);
            body.add("file", filePart);

            body.add("documentId", documentId.toString());

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body);
            ApiResponse<FileProjection> responseEntity = remoteApiCaller.post(
                    uploadServiceUrl,
                    body,
                    MediaType.MULTIPART_FORM_DATA,
                    new ParameterizedTypeReference<>() {
                    });

            if (!(responseEntity.errorCode() == null)) {
                document.markFailed("UploadService returned non-2xx: " + responseEntity.errorCode());
                documentRepository.save(document);

                sendNotification(req.getUserId(), "Document upload failed: " + responseEntity.errorCode());
                throw new DocumentProcessingException("UploadService trả về lỗi: " + responseEntity.errorCode());
            }

            // Success case
            document.markUploaded();
            documentRepository.save(document);
            sendNotification(req.getUserId(), "Document upload successful: " + req.getTitle());

            return document;
        } catch (Exception ex) {
            log.error("Lỗi khi upload tài liệu: " + ex.getMessage(), ex);
            try {
                sendNotification(req.getUserId(), "Document upload processing failed");
            } catch (Exception notifyEx) {
                log.error("Failed to send failure notification", notifyEx);
            }
            throw new DocumentProcessingException("Lỗi khi upload tài liệu: " + ex.getMessage(), ex);
        }
    }

    @Transactional(readOnly = true)
    public Document getDocumentById(UUID id) {
        return documentRepository.findById(id)
                .filter(doc -> !doc.getIsDeleted())
                .orElseThrow(() -> new DocumentNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<Document> getAllDocuments(
            org.springframework.data.domain.Pageable pageable) {
        return documentRepository.findByIsDeletedFalse(pageable);
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<Document> getDocumentsByUser(UUID userId,
            org.springframework.data.domain.Pageable pageable) {
        return documentRepository.findByUserIdAndIsDeletedFalse(userId, pageable);
    }

    @Transactional
    public Document updateDocument(UUID id, String title, String description) {
        Document document = getDocumentById(id);
        document.update(title, description);
        return documentRepository.save(document);
    }

    @Transactional
    public void deleteDocument(UUID id) {
        Document document = getDocumentById(id);
        document.markAsDeleted();
        documentRepository.save(document);
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void cleanupStuckUploads() {
        List<Document> stuckDocs = documentRepository.findByStatusAndUpdatedAtBefore(
                Document.Status.UPLOADING, LocalDateTime.now().minusMinutes(5));

        for (Document doc : stuckDocs) {
            doc.markFailed("Upload timeout after 5 minutes");
            documentRepository.save(doc);
            log.warn("Marked document {} as FAILED due to timeout", doc.getId());
        }
    }

    public boolean existsByIdAndNotDeleted(UUID id) {
        return documentRepository.existsByIdAndIsDeletedFalse(id);
    }

    private void sendNotification(UUID userId, String message) {
        try {
            Map<String, Object> notificationBody = new HashMap<>();
            notificationBody.put("userId", userId);
            notificationBody.put("senderId", userId); // Use userId as sender for now to satisfy requirement
            notificationBody.put("subject", "Thông báo từ hệ thống");
            notificationBody.put("body", message);
            notificationBody.put("type", "UPLOAD_COMPLETED");
            notificationBody.put("isRead", false);

            remoteApiCaller.post(
                    notificationServiceUrl,
                    notificationBody,
                    MediaType.APPLICATION_JSON,
                    new ParameterizedTypeReference<ApiResponse<Object>>() {
                    });
            log.info("Sent notification to user {}", userId);
        } catch (Exception e) {
            // Log warning but don't fail the transaction since DB update is already
            // committed/flushed
            log.warn("Non-blocking error: Could not send notification -> {}", e.getMessage());
        }
    }
}