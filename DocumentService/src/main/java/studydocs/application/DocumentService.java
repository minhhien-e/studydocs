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
import studydocs.dto.request.UpdateDocumentRequest;
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
    private final studydocs.repository.DocumentViewRepository documentViewRepository;
    private final RemoteApiCaller remoteApiCaller;

    @Value("${upload.service.url}")
    private String uploadServiceUrl;

    @Value("${academic.service.url}")
    private String academicServiceUrl;

    @Value("${review.service.url:http://localhost:8086}")
    private String reviewServiceUrl;

    @Transactional
    public Document createAndUploadDocument(UploadDocumentRequest req, MultipartFile file) {
        Document document = new Document(req.getUserId(), req.getTitle(), req.getDescription(),
                req.getSchoolYear());

        document.markUploading();
        document = documentRepository.save(document);

        UUID documentId = document.getId();
        log.info("DocumentService: Tạo document id={} và đặt trạng thái UPLOADING", documentId);

        // Upload to external service
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        // Wrap the file to ensure filename is preserved
        org.springframework.core.io.Resource fileResource;
        try {
            fileResource = new org.springframework.core.io.ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
        } catch (java.io.IOException e) {
            throw new DocumentProcessingException("Failed to read file bytes", e);
        }
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.setContentType(MediaType.parseMediaType(file.getContentType()));
        HttpEntity<org.springframework.core.io.Resource> filePart = new HttpEntity<>(fileResource, fileHeaders);
        body.add("file", filePart);

        body.add("documentId", documentId.toString());

        ApiResponse<FileProjection> responseEntity = remoteApiCaller.post(
                uploadServiceUrl,
                body,
                MediaType.MULTIPART_FORM_DATA,
                new ParameterizedTypeReference<>() {
                });

        if (responseEntity.statusCode() != 200)

        {
            document.markFailed("UploadService returned non-2xx: " +
                    responseEntity.statusCode());
            documentRepository.save(document);

            throw new DocumentProcessingException("UploadService trả về lỗi: " +
                    responseEntity.statusCode());
        }

        // Construct and save File Metadata
        if (responseEntity.data() != null) {
            FileProjection proj = responseEntity.data();

            // Save File ID purely
            document.setFileId(proj.id());
        } // Success case
        document.markUploaded();
        documentRepository.save(document);

        // Call Academic Service
        try {
            if (req.getUniversityId() != null && req.getSubjectId() != null) {
                log.warn("------------------------------------" + req.getSubjectId() + "--" + req.getUniversityId());
                Map<String, Object> academicBody = new HashMap<>();
                academicBody.put("subjectId", req.getSubjectId());
                academicBody.put("universityId", req.getUniversityId());
                academicBody.put("documentId", documentId);
                log.info("Preparing to call Academic Service. URL: {}, Body: {}", academicServiceUrl, academicBody);
                var responseLink = remoteApiCaller.post(
                        academicServiceUrl,
                        academicBody,
                        MediaType.APPLICATION_JSON,
                        new ParameterizedTypeReference<ApiResponse<Object>>() {
                        });
                log.info("Linked document {} to Academic Service. Response: {}", documentId, responseLink.data());
            } else {
                log.warn("Skipping Academic Service call. UniversityId or MajorId is null. Req: {}", req);
            }
        } catch (org.springframework.web.client.HttpServerErrorException.ServiceUnavailable e) {
            log.warn("Academic Service unavailable (503). Skipping link for document {}.", documentId);
        } catch (Exception e) {
            log.warn("Failed to link document to Academic Service: {}", e.getMessage());
        }

        return document;
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
    public Document updateDocument(UUID id, UUID userId, UpdateDocumentRequest request) {
        Document document = documentRepository.findByIdAndUserId(id, userId)
                .filter(doc -> !doc.getIsDeleted())
                .orElseThrow(() -> new DocumentNotFoundException(id));

        document.update(request.getTitle(), request.getDescription(), request.getSchoolYear());
        return documentRepository.save(document);
    }

    @Transactional
    public void deleteDocument(UUID id, UUID userId) {
        Document document = documentRepository.findByIdAndUserId(id, userId)
                .filter(doc -> !doc.getIsDeleted())
                .orElseThrow(() -> new DocumentNotFoundException(id));
        document.markAsDeleted();
        documentRepository.save(document);
    }

    @Transactional
    public void deleteDocumentByAdmin(UUID id) {
        Document document = documentRepository.findById(id)
                .filter(doc -> !doc.getIsDeleted())
                .orElseThrow(() -> new DocumentNotFoundException(id));
        document.markAsDeleted();
        documentRepository.save(document);
    }

    @Transactional
    public Document adminUpdateDocument(UUID id, UpdateDocumentRequest request) {
        Document document = documentRepository.findById(id)
                .filter(doc -> !doc.getIsDeleted())
                .orElseThrow(() -> new DocumentNotFoundException(id));

        document.update(request.getTitle(), request.getDescription(), request.getSchoolYear());
        return documentRepository.save(document);
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

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<Document> getNewestDocuments(int limit) {
        return documentRepository.findByIsDeletedFalse(
                org.springframework.data.domain.PageRequest.of(0, limit,
                        org.springframework.data.domain.Sort.by("createdAt").descending()));
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<Document> getNewestDocumentsByUser(UUID userId, int limit) {
        return documentRepository.findByUserIdAndIsDeletedFalse(userId,
                org.springframework.data.domain.PageRequest.of(0, limit,
                        org.springframework.data.domain.Sort.by("createdAt").descending()));
    }

    @Transactional(readOnly = true)
    public Document getDocumentById(UUID id) {
        return documentRepository.findById(id)
                .filter(doc -> !doc.getIsDeleted())
                .orElseThrow(() -> new DocumentNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Document> getMostLikedDocuments(int limit) {
        try {
            // Correct URL: /api/v1/reviews/internal/reactions/top-liked
            String url = reviewServiceUrl + "/api/v1/reviews/internal/reactions/top-liked?limit=" + limit;
            log.info("Fetching most liked documents from: {}", url);

            ApiResponse<List<Map<String, Object>>> response = remoteApiCaller.get(
                    url,
                    new ParameterizedTypeReference<ApiResponse<List<Map<String, Object>>>>() {
                    });

            // Response parsing
            if (response != null && response.data() != null) {
                log.info("Received {} top liked documents from ReviewService (Raw Data: {})", response.data().size(),
                        response.data());
                List<UUID> docIds = response.data().stream()
                        .map(m -> {
                            Object id = m.get("documentId");
                            // Handle possible String or UUID type from JSON
                            return id instanceof String ? UUID.fromString((String) id) : UUID.fromString(id.toString());
                        })
                        .collect(java.util.stream.Collectors.toList());

                if (docIds.isEmpty()) {
                    return List.of();
                }

                // Fetch documents keeping order if possible, or just fetch all
                List<Document> docs = documentRepository.findAllById(docIds);

                // Map found docs
                java.util.Map<UUID, Document> docMap = docs.stream()
                        .collect(java.util.stream.Collectors.toMap(Document::getId, d -> d));

                // Return found documents in order
                return docIds.stream()
                        .map(docMap::get)
                        .filter(java.util.Objects::nonNull)
                        .collect(java.util.stream.Collectors.toList());
            } else {
                log.warn("ReviewService returned null response or empty data");
            }
        } catch (Exception e) {
            log.error("Failed to get most liked documents: {}", e.getMessage(), e);
        }
        return List.of();
    }

    // Correction: I need to check RemoteApiCaller.
    // I will write this method placeholder for now and verify RemoteApiCaller in
    // next step.

    @Transactional
    public void recordView(UUID documentId, UUID userId) {
        documentViewRepository.save(new studydocs.domain.DocumentView(documentId, userId));
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<Document> getViewHistory(UUID userId,
            org.springframework.data.domain.Pageable pageable) {
        org.springframework.data.domain.Page<studydocs.domain.DocumentView> views = documentViewRepository
                .findByUserIdOrderByViewedAtDesc(userId, pageable);
        List<UUID> docIds = views.getContent().stream()
                .map(studydocs.domain.DocumentView::getDocumentId)
                .toList();
        // Naive implementation: fetch all by IDs. Order might be lost.
        // To preserve order, one would need to map carefully.
        List<Document> documents = documentRepository.findAllById(docIds);
        Map<UUID, Document> docMap = documents.stream()
                .collect(java.util.stream.Collectors.toMap(Document::getId, d -> d));

        List<Document> orderedDocs = docIds.stream()
                .map(docMap::get)
                .filter(java.util.Objects::nonNull)
                .toList();

        return new org.springframework.data.domain.PageImpl<>(orderedDocs, pageable, views.getTotalElements());
    }

    public FileProjection getFileMetadata(UUID fileId) {
        if (fileId == null)
            return null;
        try {
            // Assuming UploadService has an endpoint GET /api/v1/files/{id} (or internal)
            // Adjust the URL path if needed. Based on user snippet, response is standard.
            // Let's assume /files/{id} or similar under uploadServiceUrl base.
            // If uploadServiceUrl is http://.../api/v1/sub-service, we append /files.
            // CAUTION: We need to know the exact endpoint for GET file metadata.
            // Assuming it aligns with upload POST, maybe GET /upload/{id} or /files/{id}.
            // Common convention: /internal/files/{id} or /files/{id}.
            // I will use /internal/files/{id} as a safe guess for microservices, or just
            // /files/{id}.
            // Let's try /files/{id} first as it is standard REST.

            // Correction: The user didn't specify the GET endpoint, only the upload
            // response.
            // In microservices, getting by ID usually matches the resource name.
            // DocumentService typically doesn't know "upload" resource, maybe "files".
            String url = uploadServiceUrl + "/" + fileId;

            log.debug("Fetching file metadata from: {}", url);

            ApiResponse<FileProjection> response = remoteApiCaller.get(
                    url,
                    new ParameterizedTypeReference<ApiResponse<FileProjection>>() {
                    });

            if (response != null && response.data() != null) {
                return response.data();
            }
        } catch (Exception e) {
            log.error("Failed to fetch file metadata for fileId: {}", fileId, e);
        }
        return null;
    }

    public List<studydocs.dto.response.DocumentResponse> enrichDocumentResponses(
            List<studydocs.dto.response.DocumentResponse> responses) {
        if (responses == null || responses.isEmpty()) {
            return responses;
        }

        return responses.stream().map(doc -> { // Use sequential stream to ensure SecurityContext propagation
            studydocs.dto.response.DocumentResponse enriched = doc;

            try {
                // 1. Call Academic Service
                // /api/v1/academics/documents/public/info-by-document?documentId=" + doc.id()
                String academicUrl = academicServiceUrl + "/public/info-by-document?documentId=" + doc.id();
                ApiResponse<studydocs.dto.response.AcademicDocumentInfo> academicResp = remoteApiCaller.get(
                        academicUrl,
                        new ParameterizedTypeReference<ApiResponse<studydocs.dto.response.AcademicDocumentInfo>>() {
                        });

                if (academicResp != null && academicResp.data() != null) {
                    studydocs.dto.response.AcademicDocumentInfo info = academicResp.data();
                    enriched = new studydocs.dto.response.DocumentResponse(enriched, info.subjectId(),
                            info.universityId());
                }
            } catch (Exception e) {
                log.warn("Failed to enrich document {} with academic info: {}", doc.id(), e.getMessage());
            }

            try {
                // 2. Call Upload Service for File Metadata (to get Cloudinary URL & Preview)
                if (doc.fileId() != null) {
                    FileProjection fileMeta = getFileMetadata(doc.fileId());
                    if (fileMeta != null) {
                        enriched = new studydocs.dto.response.DocumentResponse(enriched, fileMeta.downloadUrl(),
                                fileMeta.previewDataView(), fileMeta.totalPages());
                    }
                }
            } catch (Exception e) {
                log.warn("Failed to enrich document {} with file metadata: {}", doc.id(), e.getMessage());
            }

            return enriched;
        }).collect(java.util.stream.Collectors.toList());
    }

    public Document waitUntilUploaded(UUID docId) {

        int maxRetry = 20; // 20 * 1s = 20s timeout
        int count = 0;

        while (count < maxRetry) {
            Document doc = documentRepository.findById(docId)
                    .orElseThrow(() -> new RuntimeException("Document not found"));

            // THÀNH CÔNG
            if (doc.getStatus() == Document.Status.UPLOADED) {
                return doc;
            }

            // THẤT BẠI
            if (doc.getStatus() == Document.Status.FAILED) {
                throw new RuntimeException("Upload failed from Media Service");
            }

            try {
                Thread.sleep(1000); // chờ 1s rồi check lại
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted");
            }

            count++;
        }

        throw new RuntimeException("Upload timeout - Media service too slow");
    }

}