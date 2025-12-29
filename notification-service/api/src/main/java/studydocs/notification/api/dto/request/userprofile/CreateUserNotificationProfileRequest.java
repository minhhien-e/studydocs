package studydocs.notification.api.dto.request.userprofile;

import lombok.Data;

@Data
public class CreateUserNotificationProfileRequest {
    private String email;
    private String phoneNumber;
}
