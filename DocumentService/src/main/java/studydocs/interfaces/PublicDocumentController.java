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
        return ResponseEntity.ok(ApiResponse.success(200, docPage.map(DocumentResponse::new)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DocumentResponse>> getDocumentById(@PathVariable UUID id) {
        Document document = documentService.getDocumentById(id);

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

        return ResponseEntity.ok(ApiResponse.success(200, new DocumentResponse(document)));
    }

    @GetMapping("/{id}/exists")
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
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @GetMapping("/most-liked")
    public ResponseEntity<ApiResponse<List<DocumentResponse>>> getMostLikedDocuments(
            @RequestParam(defaultValue = "10") int limit) {
        List<Document> docs = documentService.getMostLikedDocuments(limit);
        List<DocumentResponse> response = docs.stream()
                .map(DocumentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }
}
