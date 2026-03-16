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
@RequestMapping("/api/v1/internal/reactions")
@RequiredArgsConstructor
public class InternalReviewController {

    private final AdminStatsService adminStatsService;

    @PostMapping("/count-batch")
    public ResponseEntity<ApiResponse<Long>> countLikesForDocuments(@RequestBody List<UUID> documentIds) {
        return ResponseEntity.ok(ApiResponse.success(200, adminStatsService.countLikesForDocuments(documentIds)));
    }
}
