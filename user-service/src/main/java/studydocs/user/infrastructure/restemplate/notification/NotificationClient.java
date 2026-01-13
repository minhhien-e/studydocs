package studydocs.user.infrastructure.restemplate.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import studydocs.user.interfaces.model.NotifyRegisterSuccessRequest;

public interface NotificationClient {
    void notifyRegisterSuccess(NotifyRegisterSuccessRequest request) throws JsonProcessingException;
}
