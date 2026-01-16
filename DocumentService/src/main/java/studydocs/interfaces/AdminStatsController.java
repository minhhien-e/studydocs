package studydocs.interfaces;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.application.AdminStatsService;
import studydocs.dto.response.ApiResponse;

import java.util.Map;

@RestController
// @PreAuthorize("hasRole('ADMIN')")
@PreAuthorize("hasAuthority('SCOPE_READ_USER')")
@RequestMapping("/api/v1/documents/admin/stats")
@RequiredArgsConstructor
public class AdminStatsController {

    private final AdminStatsService adminStatsService;

    @GetMapping("/today")
    public ResponseEntity<ApiResponse<Long>> getDocumentsUploadedToday() {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.getDocumentsUploadedToday()));
    }

    @GetMapping("/system")
    public ResponseEntity<ApiResponse<Long>> getSystemUploadStats(
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "day") String period) {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.getSystemUploads(period)));
    }

    @GetMapping("/users/{userId}/documents")
    public ResponseEntity<ApiResponse<Long>> getUserUploadStats(
            @org.springframework.web.bind.annotation.PathVariable java.util.UUID userId,
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "day") String period) {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.getUserUploads(userId, period)));
    }

    @GetMapping("/users/{userId}/total")
    public ResponseEntity<ApiResponse<Long>> getUserTotalUploads(
            @org.springframework.web.bind.annotation.PathVariable java.util.UUID userId) {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.getUserTotalUploads(userId)));
    }

    @GetMapping("/documents/total")
    public ResponseEntity<ApiResponse<Long>> getTotalDocuments() {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.getTotalDocuments()));
    }

    @GetMapping("/documents/uploads-by-month")
    public ResponseEntity<ApiResponse<java.util.List<java.util.Map<String, Object>>>> getMonthlyUploads() {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.getMonthlyUploads()));
    }

    @GetMapping("/users/{userId}/likes-received")
    public ResponseEntity<ApiResponse<Long>> getUserTotalLikesReceived(
            @org.springframework.web.bind.annotation.PathVariable java.util.UUID userId) {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.getTotalLikesReceived(userId)));
    }
}
