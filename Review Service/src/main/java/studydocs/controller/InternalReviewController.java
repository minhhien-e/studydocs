package studydocs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.dto.response.ApiResponse;
import studydocs.service.AdminStatsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews/internal/reactions")
@RequiredArgsConstructor
public class InternalReviewController {

    private final AdminStatsService adminStatsService;

    @PostMapping("/count-batch")
    public ResponseEntity<ApiResponse<Long>> countLikesForDocuments(@RequestBody List<UUID> documentIds) {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.countLikesForDocuments(documentIds)));
    }

    @org.springframework.web.bind.annotation.GetMapping("/top-liked")
    public ResponseEntity<ApiResponse<List<studydocs.model.DocumentStats>>> getTopLikedDocuments(
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "10") int limit) {
        // limit is not used in service for now (fixed to 10), but good to have in
        // signature
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.getTopLikedDocuments()));
    }

    //thêm hàm api/v1/internal/documents/id
}
