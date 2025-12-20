package studydocs.notification.application.port.out.provider;

import studydocs.notification.application.dto.payload.base.DataProvidePayload;

import java.util.Map;
import java.util.UUID;

public interface NotificationDataProvider<Payload extends DataProvidePayload> {
    String getSupportPrefix();

    String getGroupName();

    Map<String, Object> getData(Payload payload);

    Map<String, String> getAvailableMetadata();
}
