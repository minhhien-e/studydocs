package studydocs.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import studydocs.dto.response.ApiResponse;
import studydocs.exception.DocumentValidationException;
import studydocs.exception.RemoteException;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DocumentClient {

    private final RemoteApiCaller remoteApiCaller;

    @Value("${document.service.url}")
    private String documentServiceUrl;

    public void validateDocumentId(UUID documentId) {
        String url = documentServiceUrl + "/public/" + documentId + "/exists";
        ParameterizedTypeReference<ApiResponse<Boolean>> type = new ParameterizedTypeReference<>() {
        };

        try {
            Boolean exists = remoteApiCaller.get(url, type, documentId);

            if (!Boolean.TRUE.equals(exists)) {
                throw new DocumentValidationException();
            }

        } catch (RemoteException ex) {
            throw ex;
        }
    }

    public studydocs.dto.response.DocumentResponse getDocumentById(UUID documentId) {
        String url = documentServiceUrl + "/public/" + documentId;
        ParameterizedTypeReference<ApiResponse<studydocs.dto.response.DocumentResponse>> type = new ParameterizedTypeReference<>() {
        };

        try {
            studydocs.dto.response.DocumentResponse response = remoteApiCaller.get(url, type, documentId);
            return response;
        } catch (RemoteException ex) {
            // If fetching failed, maybe return null or propagate. Propagating is better.
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch document details: " + e.getMessage());
        }
    }
}
