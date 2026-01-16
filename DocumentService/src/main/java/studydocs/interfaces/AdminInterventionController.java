package studydocs.interfaces;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studydocs.application.AdminInterventionService;
import studydocs.dto.response.ApiResponse;

import java.util.UUID;

@RestController
@PreAuthorize("hasAuthority('SCOPE_READ_USER')")
@RequestMapping("/api/v1/documents/admin")
@RequiredArgsConstructor
public class AdminInterventionController {

    private final AdminInterventionService adminInterventionService;

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUserDocuments(@PathVariable UUID userId) {
        adminInterventionService.deleteUserDocuments(userId);
        return ResponseEntity.ok(ApiResponse.success(200, "All documents for user " + userId + " have been deleted."));
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<ApiResponse<String>> deleteDocument(@PathVariable UUID documentId) {
        adminInterventionService.deleteDocument(documentId);
        return ResponseEntity.ok(ApiResponse.success(200, "Document " + documentId + " has been deleted."));
    }
}
