package studydocs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.dto.response.ApiResponse;
import studydocs.service.AdminStatsService;

import java.util.Map;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1/reviews/admin/stats")
@RequiredArgsConstructor
public class AdminStatsController {

    private final AdminStatsService adminStatsService;

    @GetMapping("/reviews/total")
    public ResponseEntity<ApiResponse<Long>> getTotalReviews() {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.getTotalReviews()));
    }

    @GetMapping("/reactions/total-likes")
    public ResponseEntity<ApiResponse<Long>> getTotalDocumentLikes() {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.getTotalDocumentLikes()));
    }
}
