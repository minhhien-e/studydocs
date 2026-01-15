package studydocs.notification.api.dto.request.userprofile;

import lombok.Data;

@Data
public class UpdateNotificationPreferencesRequest {
    private boolean pushEnabled;
    private boolean emailEnabled;
    private boolean smsEnabled;
}
