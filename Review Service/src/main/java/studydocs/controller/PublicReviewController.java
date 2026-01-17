package studydocs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.dto.response.ApiResponse;
import studydocs.dto.response.ReviewResponse;
import studydocs.service.AdminStatsService;
import studydocs.service.ReviewQueryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews/public")
@RequiredArgsConstructor
public class PublicReviewController {

    private final AdminStatsService adminStatsService;
    private final ReviewQueryService queryService;

    @GetMapping("/top-liked")
    public ResponseEntity<ApiResponse<List<studydocs.model.DocumentStats>>> getTopLikedDocuments(
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.getTopLikedDocuments()));
    }

    @GetMapping("/document/{docId}")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getByDocument(
            @PathVariable UUID docId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(200,
                queryService.getReviewsByDocumentId(docId, PageRequest.of(page, size))));
    }
}
