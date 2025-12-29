package studydocs.notification.infrastructure.adapter.repository.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.notification.application.dto.projection.FileProjection;
import studydocs.notification.application.port.out.remote.RemoteFileServicePort;
import studydocs.notification.application.port.out.repository.FileQueries;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FileQueryAdapter implements FileQueries {
    private final RemoteFileServicePort remoteFileServicePort;

    @Override
    public FileProjection getById(UUID id) {
        return remoteFileServicePort.getById(id);
    }
}
