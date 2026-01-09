package studydocs.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studydocs.application.DocumentService;
import studydocs.domain.Document;
import studydocs.dto.request.UploadDocumentRequest;
import studydocs.dto.response.ApiResponse;
import studydocs.dto.response.DocumentResponse;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    /**
     * Upload document
     * Yêu cầu quyền WRITE_USER
     */
    @PostMapping(consumes = "multipart/form-data")
    // @PreAuthorize("hasAuthority('WRITE_USER')")
    public ResponseEntity<ApiResponse<DocumentResponse>> uploadDocument(
            @RequestPart("data") UploadDocumentRequest data,
            @RequestPart("file") MultipartFile file) throws JsonProcessingException {

        Document doc = documentService.createAndUploadDocument(data, file);
        return ResponseEntity.accepted()
                .body(ApiResponse.success(202, new DocumentResponse(doc)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<org.springframework.data.domain.Page<DocumentResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        org.springframework.data.domain.Page<Document> docPage = documentService.getAllDocuments(
                org.springframework.data.domain.PageRequest.of(page, size,
                        org.springframework.data.domain.Sort.by("createdAt").descending()));
        return ResponseEntity.ok(ApiResponse.success(200, docPage.map(DocumentResponse::new)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<org.springframework.data.domain.Page<DocumentResponse>>> getByUser(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        org.springframework.data.domain.Page<Document> docPage = documentService.getDocumentsByUser(
                userId,
                org.springframework.data.domain.PageRequest.of(page, size,
                        org.springframework.data.domain.Sort.by("createdAt").descending()));
        return ResponseEntity.ok(ApiResponse.success(200, docPage.map(DocumentResponse::new)));
    }

    /**
     * Get document by id
     */
    @GetMapping("/{id}")
    // @PreAuthorize("hasAuthority('WRITE_USER')")
    public ResponseEntity<ApiResponse<DocumentResponse>> getDocumentById(
            @PathVariable UUID id) {
        Document document = documentService.getDocumentById(id);
        return ResponseEntity.ok(ApiResponse.success(200, new DocumentResponse(document)));
    }

    /**
     * Check document exists
     */
    @GetMapping("/{id}/exists")
    // @PreAuthorize("hasAuthority('WRITE_USER')")
    public ResponseEntity<ApiResponse<Boolean>> exists(
            @PathVariable UUID id) {
        boolean exists = documentService.existsByIdAndNotDeleted(id);
        return ResponseEntity.ok(ApiResponse.success(200, exists));
    }

    /**
     * Update document
     */
    @PutMapping("/{id}")
    // @PreAuthorize("hasAuthority('WRITE_USER')")
    public ResponseEntity<ApiResponse<DocumentResponse>> updateDocument(
            @PathVariable UUID id,
            @RequestParam String title,
            @RequestParam String description) {
        Document document = documentService.updateDocument(id, title, description);
        return ResponseEntity.ok(ApiResponse.success(200, new DocumentResponse(document)));
    }

    /**
     * Delete document
     */
    @DeleteMapping("/{id}")
    // @PreAuthorize("hasAuthority('WRITE_USER')")
    public ResponseEntity<ApiResponse<String>> deleteDocument(
            @PathVariable UUID id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok(
                ApiResponse.success(200, "Document deleted: " + id));
    }
}
