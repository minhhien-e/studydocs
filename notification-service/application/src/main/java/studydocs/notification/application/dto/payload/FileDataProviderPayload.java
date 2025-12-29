package studydocs.notification.application.dto.payload;

import studydocs.notification.application.dto.payload.base.DataProvidePayload;

import java.util.UUID;

public record FileDataProviderPayload(UUID fileId) implements DataProvidePayload {
}
