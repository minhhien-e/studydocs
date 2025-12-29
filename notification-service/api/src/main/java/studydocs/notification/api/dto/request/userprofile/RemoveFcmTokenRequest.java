package studydocs.notification.api.dto.request.userprofile;

import lombok.Data;

@Data
public class RemoveFcmTokenRequest {
    private String fcmToken;
}
