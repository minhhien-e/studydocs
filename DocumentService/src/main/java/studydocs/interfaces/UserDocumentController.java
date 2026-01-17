package studydocs.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studydocs.application.DocumentService;
import studydocs.domain.Document;
import studydocs.dto.request.UploadDocumentRequest;
import studydocs.dto.response.ApiResponse;
import studydocs.dto.response.DocumentResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/documents/user")
@RequiredArgsConstructor
@lombok.extern.slf4j.Slf4j
// @PreAuthorize("isAuthenticated()") // Base requirement
public class UserDocumentController {

    private final DocumentService documentService;

    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    @PostMapping(consumes = "multipart/form-data")
    // @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<DocumentResponse>> uploadDocument(
            @RequestPart("data") String dataString,
            @RequestPart("file") MultipartFile file) throws JsonProcessingException {
        UploadDocumentRequest data = objectMapper.readValue(dataString, UploadDocumentRequest.class);
        // Force UserID from Auth
        data.setUserId(getUserIdFromAuth());

        log.info("Received upload request. Data: {}, File: {}", data, file.getOriginalFilename());
        Document doc = documentService.createAndUploadDocument(data, file);
        return ResponseEntity.accepted()
                .body(ApiResponse.success(202, new DocumentResponse(doc)));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasAuthority('SCOPE_WRITE_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<DocumentResponse>> updateDocument(
            @PathVariable UUID id,
            @RequestBody studydocs.dto.request.UpdateDocumentRequest request) {
        Document document = documentService.updateDocument(id, getUserIdFromAuth(), request);
        return ResponseEntity.ok(ApiResponse.success(200, new DocumentResponse(document)));
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasAuthority('SCOPE_WRITE_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<String>> deleteDocument(@PathVariable UUID id) {
        documentService.deleteDocument(id, getUserIdFromAuth());
        return ResponseEntity.ok(ApiResponse.success(200, "Document deleted: " + id));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<org.springframework.data.domain.Page<DocumentResponse>>> getMyDocuments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        UUID userId = getUserIdFromAuth();
        org.springframework.data.domain.Page<Document> docPage = documentService.getDocumentsByUser(
                userId,
                org.springframework.data.domain.PageRequest.of(page, size,
                        org.springframework.data.domain.Sort.by("createdAt").descending()));
        return ResponseEntity.ok(ApiResponse.success(200, docPage.map(DocumentResponse::new)));
    }

    @GetMapping("/me/newest")
    public ResponseEntity<ApiResponse<List<DocumentResponse>>> getMyNewestDocuments(
            @RequestParam(defaultValue = "10") int limit) {
        UUID userId = getUserIdFromAuth();
        org.springframework.data.domain.Page<Document> page = documentService.getNewestDocumentsByUser(userId, limit);
        List<DocumentResponse> response = page.getContent().stream()
                .map(DocumentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @GetMapping("/me/history")
    public ResponseEntity<ApiResponse<org.springframework.data.domain.Page<DocumentResponse>>> getViewHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        UUID userId = getUserIdFromAuth();
        org.springframework.data.domain.Page<Document> docPage = documentService.getViewHistory(
                userId, org.springframework.data.domain.PageRequest.of(page, size));
        return ResponseEntity.ok(ApiResponse.success(200, docPage.map(DocumentResponse::new)));
    }

    // Helper to get userId from Auth
    private UUID getUserIdFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
            throw new org.springframework.security.access.AccessDeniedException("User is not authenticated");
        }
        try {
            return UUID.fromString(auth.getName());
        } catch (IllegalArgumentException e) {
            log.error("Invalid User ID format in Auth Principal: {}", auth.getName());
            throw new org.springframework.security.access.AccessDeniedException("Invalid User ID in Token");
        }
    }
    // @GetMapping("/users/{userId}/total")
    // public ResponseEntity<ApiResponse<Long>> getUserTotalUploads(
    // @org.springframework.web.bind.annotation.PathVariable java.util.UUID userId)
    // {
    // return ResponseEntity.ok(ApiResponse.success(200,
    // adminStatsService.getUserTotalUploads(userId)));
    // }
}
