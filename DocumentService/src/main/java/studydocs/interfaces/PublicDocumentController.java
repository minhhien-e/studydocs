package studydocs.interfaces;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import studydocs.application.DocumentService;
import studydocs.domain.Document;
import studydocs.dto.response.ApiResponse;
import studydocs.dto.response.DocumentResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/documents/public")
@RequiredArgsConstructor
public class PublicDocumentController {

    private final DocumentService documentService;

    @GetMapping
    public ResponseEntity<ApiResponse<org.springframework.data.domain.Page<DocumentResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        org.springframework.data.domain.Page<Document> docPage = documentService.getAllDocuments(
                org.springframework.data.domain.PageRequest.of(page, size,
                        org.springframework.data.domain.Sort.by("createdAt").descending()));
        List<DocumentResponse> responseList = docPage.stream()
                .map(DocumentResponse::new)
                .collect(Collectors.toList());
        responseList = documentService.enrichDocumentResponses(responseList);
        return ResponseEntity.ok(ApiResponse.success(200, new org.springframework.data.domain.PageImpl<>(
                responseList, docPage.getPageable(), docPage.getTotalElements())));
    }

    @DeleteMapping("/{id:[0-9a-fA-F-]{36}}")
    public ResponseEntity<ApiResponse<String>> deleteDocument(@PathVariable UUID id) {
        documentService.deleteDocumentByAdmin(id);
        return ResponseEntity.ok(ApiResponse.success(200, "Document deleted by admin: " + id));
    }

    @GetMapping("/{id:[0-9a-fA-F-]{36}}")
    public ResponseEntity<ApiResponse<DocumentResponse>> getDocumentById(@PathVariable UUID id) {
        Document document = documentService.getDocumentById(id);

        studydocs.dto.projection.FileProjection fileMeta = null;
        if (document.getFileId() != null) {
            try {
                fileMeta = documentService.getFileMetadata(document.getFileId());
            } catch (Exception e) {
                // Log error but proceed to return document metadata without file details
                System.err.println("Failed to fetch file metadata from UploadService: " + e.getMessage());
            }
        }

        // Try to record view if user is authenticated
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
                // Assuming the subject is the UUID
                // If using JWT, usually the subject is the userId or we extract it from claims.
                // Assuming JwtAuthenticationConverter sets the name to sub or similar.
                // Let's try to parse auth.getName() as UUID.
                try {
                    UUID userId = UUID.fromString(auth.getName());
                    documentService.recordView(id, userId);
                } catch (IllegalArgumentException e) {
                    // Ignore if not UUID (maybe username based auth?)
                }
            }
        } catch (Exception e) {
            // Ignore view recording errors
        }

        DocumentResponse response = new DocumentResponse(
                document,
                fileMeta != null ? fileMeta.downloadUrl() : null,
                fileMeta != null ? fileMeta.previewDataView() : null);

        // Enrich single document
        List<DocumentResponse> enrichedList = documentService.enrichDocumentResponses(List.of(response));
        if (!enrichedList.isEmpty()) {
            response = enrichedList.get(0);
        }

        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @GetMapping("/{id:[0-9a-fA-F-]{36}}/exists")
    public ResponseEntity<ApiResponse<Boolean>> exists(@PathVariable UUID id) {
        boolean exists = documentService.existsByIdAndNotDeleted(id);
        return ResponseEntity.ok(ApiResponse.success(200, exists));
    }

    @GetMapping("/newest")
    public ResponseEntity<ApiResponse<List<DocumentResponse>>> getNewestDocuments(
            @RequestParam(defaultValue = "10") int limit) {
        org.springframework.data.domain.Page<Document> page = documentService.getNewestDocuments(limit);
        List<DocumentResponse> response = page.getContent().stream()
                .map(DocumentResponse::new)
                .collect(Collectors.toList());
        response = documentService.enrichDocumentResponses(response);
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @GetMapping("/most-liked")
    public ResponseEntity<ApiResponse<List<DocumentResponse>>> getMostLikedDocuments(
            @RequestParam(defaultValue = "10") int limit) {
        List<Document> docs = documentService.getMostLikedDocuments(limit);
        List<DocumentResponse> response = docs.stream()
                .map(DocumentResponse::new)
                .collect(Collectors.toList());
        response = documentService.enrichDocumentResponses(response);
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    // @GetMapping("/debug")
    // public ResponseEntity<ApiResponse<Object>> debugAuth() {
    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // Map<String, Object> debugInfo = new java.util.HashMap<>();
    // debugInfo.put("name", auth.getName());
    // debugInfo.put("authorities",
    // auth.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()));
    // debugInfo.put("principal", auth.getPrincipal().toString());
    // if (auth.getPrincipal() instanceof
    // org.springframework.security.oauth2.jwt.Jwt jwt) {
    // debugInfo.put("claims", jwt.getClaims());
    // }
    // return ResponseEntity.ok(ApiResponse.success(200, debugInfo));
    // }
}
