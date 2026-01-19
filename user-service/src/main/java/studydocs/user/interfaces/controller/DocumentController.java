package studydocs.user.interfaces.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import studydocs.user.application.ManageDocumentService;
import studydocs.user.infrastructure.JwtCurrentUserProvider;
import studydocs.user.interfaces.model.ApiResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/document")
@Slf4j
@RequiredArgsConstructor
public class DocumentController {

    private final ManageDocumentService manageDocumentService;
    private final JwtCurrentUserProvider jwtCurrentUserProvider;

    /**
     * Save / Unsave document
     * @param documentId ID của document
     * @return true nếu đã save, false nếu unsave
     */
    @PostMapping("/save")
    public ApiResponse<Boolean> saveDocument(
            @RequestParam UUID documentId,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId
    ) {
        UUID userId = jwtCurrentUserProvider.getCurrentUserId();

        log.info("[traceId: {}] User {} save/unsave document {}",
                traceId, userId, documentId);

        return manageDocumentService.toggleSaveDocument(
                userId,
                documentId,
                traceId
        );
    }

    /**
     * Unsave document - Xóa document khỏi danh sách đã save
     * @param documentId ID của document cần unsave
     * @return ApiResponse với thông báo xóa thành công
     */
    @DeleteMapping("/unsave")
    public ApiResponse<Boolean> unsaveDocument(
            @RequestParam UUID documentId,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId
    ) {
        UUID userId = jwtCurrentUserProvider.getCurrentUserId();

        log.info("[traceId: {}] User {} unsave document {}",
                traceId, userId, documentId);

        return manageDocumentService.unsaveDocument(
                userId,
                documentId,
                traceId
        );
    }

    /**
     * Lấy danh sách ID document đã save của user hiện tại
     */
    @GetMapping("/saved")
    public ApiResponse<List<UUID>> getSavedDocuments(
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId
    ) {
        UUID userId = jwtCurrentUserProvider.getCurrentUserId();

        log.info("[traceId: {}] Lấy danh sách document đã save của user {}",
                traceId, userId);

        return manageDocumentService.getSavedDocumentIds(
                userId,
                traceId
        );
    }
}
