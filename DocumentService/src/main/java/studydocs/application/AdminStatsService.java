package studydocs.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.repository.DocumentRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminStatsService {

    private final DocumentRepository documentRepository;

    @Transactional(readOnly = true)
    public long getDocumentsUploadedToday() {
        return getSystemUploads("day");
    }

    @Transactional(readOnly = true)
    public long getTotalDocuments() {
        return documentRepository.count();
    }

    @Transactional(readOnly = true)
    public long getSystemUploads(String period) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start;
        switch (period.toLowerCase()) {
            case "month":
                start = now.toLocalDate().withDayOfMonth(1).atStartOfDay();
                break;
            case "year":
                start = now.toLocalDate().withDayOfYear(1).atStartOfDay();
                break;
            case "day":
            default:
                start = now.toLocalDate().atStartOfDay();
                break;
        }
        return documentRepository.countByCreatedAtBetween(start, now);
    }

    @Transactional(readOnly = true)
    public long getUserUploads(java.util.UUID userId, String period) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start;
        switch (period.toLowerCase()) {
            case "month":
                start = now.toLocalDate().withDayOfMonth(1).atStartOfDay();
                break;
            case "year":
                start = now.toLocalDate().withDayOfYear(1).atStartOfDay();
                break;
            case "day":
            default:
                start = now.toLocalDate().atStartOfDay();
                break;
        }
        return documentRepository.countByUserIdAndCreatedAtBetween(userId, start, now);
    }

    @Transactional(readOnly = true)
    public long getUserTotalUploads(java.util.UUID userId) {
        return documentRepository.countByUserIdAndIsDeletedFalse(userId);
    }

    @Transactional(readOnly = true)
    public java.util.List<java.util.Map<String, Object>> getMonthlyUploads() {
        // Simple in-memory aggregation
        java.util.List<studydocs.domain.Document> allDocs = documentRepository.findAll();
        java.util.Map<String, Long> grouped = allDocs.stream()
                .filter(d -> !d.getIsDeleted() && d.getCreatedAt() != null)
                .collect(java.util.stream.Collectors.groupingBy(
                        d -> d.getCreatedAt().getYear() + "-" + d.getCreatedAt().getMonthValue(),
                        java.util.stream.Collectors.counting()));

        java.util.List<java.util.Map<String, Object>> result = new java.util.ArrayList<>();
        grouped.forEach((k, v) -> {
            String[] parts = k.split("-");
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("year", Integer.parseInt(parts[0]));
            map.put("month", Integer.parseInt(parts[1]));
            map.put("count", v);
            result.add(map);
        });

        // Sort by year desc, month desc
        result.sort((a, b) -> {
            int yA = (int) a.get("year");
            int yB = (int) b.get("year");
            if (yA != yB)
                return yB - yA;
            return (int) b.get("month") - (int) a.get("month");
        });

        return result;
    }

    private final studydocs.client.RemoteApiCaller remoteApiCaller;

    @org.springframework.beans.factory.annotation.Value("${review.service.url:http://localhost:9051}")
    private String reviewServiceUrl;

    public long getTotalLikesReceived(java.util.UUID userId) {
        java.util.List<java.util.UUID> docIds = documentRepository.findIdsByUserIdAndIsDeletedFalse(userId);
        if (docIds.isEmpty())
            return 0;

        try {
            String url = reviewServiceUrl + "/api/v1/internal/reactions/count-batch";
            studydocs.dto.response.ApiResponse<Long> response = remoteApiCaller.post(
                    url,
                    docIds,
                    org.springframework.http.MediaType.APPLICATION_JSON,
                    new org.springframework.core.ParameterizedTypeReference<studydocs.dto.response.ApiResponse<Long>>() {
                    });
            if (response != null && response.data() != null) {
                return response.data();
            }
        } catch (Exception e) {
            // Fallback or log error
            System.err.println("Error calling ReviewService for likes: " + e.getMessage());
        }
        return 0;
    }
}
