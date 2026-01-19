package studydocs.user.application;

import studydocs.user.interfaces.model.ApiResponse;

import java.util.List;
import java.util.UUID;

public interface ManageDocumentService {
    ApiResponse<Boolean> toggleSaveDocument(UUID userId, UUID documentId, String traceId);
    ApiResponse<Boolean> unsaveDocument(UUID userId, UUID documentId, String traceId);
    ApiResponse<List<UUID>> getSavedDocumentIds(UUID userId, String traceId);
}
