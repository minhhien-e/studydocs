package studydocs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import studydocs.client.RemoteApiCaller;
import studydocs.dto.request.NotificationRequest;
import studydocs.dto.response.ApiResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final RemoteApiCaller remoteApiCaller;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    public void send(UUID userId, UUID senderId, String subject, String body, String type) {
        try {
            NotificationRequest notificationBody = NotificationRequest.builder()
                    .userId(userId)
                    .senderId(senderId != null ? senderId : userId) // Default to self or system
                    .subject(subject)
                    .body(body)
                    .type(type)
                    .isRead(false)
                    .build();

            remoteApiCaller.post(
                    notificationServiceUrl,
                    new ParameterizedTypeReference<ApiResponse<Object>>() {
                    },
                    new HttpEntity<>(notificationBody));
        } catch (Exception e) {
            // Log but don't fail transaction
            System.err.println("Failed to send notification: " + e.getMessage());
        }
    }
}
