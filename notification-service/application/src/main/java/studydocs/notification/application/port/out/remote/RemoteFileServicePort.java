package studydocs.notification.application.port.out.remote;

import studydocs.notification.application.dto.projection.FileProjection;

import java.util.UUID;

public interface RemoteFileServicePort {
    FileProjection getById(UUID id);
}
