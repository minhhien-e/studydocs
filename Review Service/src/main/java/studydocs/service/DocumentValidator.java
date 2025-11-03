package studydocs.service;

import studydocs.exception.DocumentValidationException;
import studydocs.model.Review;
import studydocs.dto.request.CreateReviewRequest;
import studydocs.dto.response.ReviewResponse;
import studydocs.exception.ReviewNotFoundException;
import studydocs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DocumentValidator {

    private final RestTemplate restTemplate;

    @Value("${document.service.url:}")
    private String documentServiceUrl;

    public void validateDocumentId(java.util.UUID documentId) {
        try {
            String docUrl = documentServiceUrl + "/" + documentId;
            ResponseEntity<String> response = restTemplate.getForEntity(docUrl, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new DocumentValidationException(602);  // DOCUMENT_INVALID
            }
        } catch (Exception ex) {
            throw new DocumentValidationException(603);  // SYSTEM_ERROR
        }
    }
}
