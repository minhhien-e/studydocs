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
        String url = documentServiceUrl + "/{id}/exists";
        ParameterizedTypeReference<ApiResponse<Boolean>> type =
                new ParameterizedTypeReference<>() {};

        try {
            Boolean exists = remoteApiCaller.get(url, type, documentId);

            if (!Boolean.TRUE.equals(exists)) {
                throw new DocumentValidationException();
            }

        } catch (RemoteException ex) {
            throw ex;
        }
    }
}
