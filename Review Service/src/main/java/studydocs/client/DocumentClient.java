package studydocs.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import studydocs.exception.DocumentValidationException;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentClient {

    private final RestTemplate restTemplate;

    @Value("${document.service.url:http://localhost:9050/documents}")
    private String documentServiceUrl;

    public void validateDocumentId(UUID documentId) {
        try {
            String docUrl = documentServiceUrl + "/" + documentId;
            HttpHeaders headers = new HttpHeaders();
            String token = getCurrentUserToken();
            if (token != null) {
                headers.set("Authorization", "Bearer " + token);
                log.debug("Forwarding token to Document Service for docId: {}", documentId);
            } else {
                log.warn("No auth token found, calling Document Service anonymously for docId: {}", documentId);
            }
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(docUrl, HttpMethod.GET, entity, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("Document validation failed for ID {}: status {}", documentId, response.getStatusCode());
                throw new DocumentValidationException(602);  // DOCUMENT_INVALID
            }
        } catch (Exception ex) {
            log.error("Exception during document validation for ID {}: {}", documentId, ex.getMessage(), ex);
            throw new DocumentValidationException(603);  // SYSTEM_ERROR
        }
    }

    private String getCurrentUserToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            return jwtAuth.getToken().getTokenValue();  // Lấy JWT string thật
        }
        return null;  // Nếu public call, OK; không thì throw nếu cần strict
    }
}