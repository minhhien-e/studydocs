package studydocs.interfaces;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.application.DocumentService;
import studydocs.domain.Document;
import studydocs.dto.response.ApiResponse;
import studydocs.dto.response.DocumentResponse;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/internal/documents")
@RequiredArgsConstructor
public class InternalDocumentController {

    private final DocumentService documentService;

    @GetMapping("/{id:[0-9a-fA-F-]{36}}")
    public ResponseEntity<ApiResponse<DocumentResponse>> getDocumentById(@PathVariable UUID id) {
        Document document = documentService.getDocumentById(id);
        var enrichedList = documentService.enrichDocumentResponses(java.util.List.of(new DocumentResponse(document)));
        return ResponseEntity.ok(ApiResponse.success(200, enrichedList.get(0)));
    }

    @GetMapping("/{id:[0-9a-fA-F-]{36}}/exists")
    public ResponseEntity<ApiResponse<Boolean>> exists(@PathVariable UUID id) {
        boolean exists = documentService.existsByIdAndNotDeleted(id);
        return ResponseEntity.ok(ApiResponse.success(200, exists));
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id:[0-9a-fA-F-]{36}}")
    public ResponseEntity<ApiResponse<Void>> deleteDocument(@PathVariable UUID id) {
        documentService.deleteDocumentByAdmin(id);
        return ResponseEntity.ok(ApiResponse.success(200, null));
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id:[0-9a-fA-F-]{36}}")
    public ResponseEntity<ApiResponse<DocumentResponse>> updateDocumentByAdmin(@PathVariable UUID id,
            @org.springframework.web.bind.annotation.RequestBody studydocs.dto.request.UpdateDocumentRequest request) {
        Document document = documentService.adminUpdateDocument(id, request);
        var enrichedList = documentService.enrichDocumentResponses(java.util.List.of(new DocumentResponse(document)));
        return ResponseEntity.ok(ApiResponse.success(200, enrichedList.get(0)));
    }
}
