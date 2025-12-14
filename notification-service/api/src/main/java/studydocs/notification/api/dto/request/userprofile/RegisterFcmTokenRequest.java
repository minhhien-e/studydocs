package studydocs.notification.api.dto.request.userprofile;

import lombok.Data;

@Data
public class RegisterFcmTokenRequest {
    private String fcmToken;
}
