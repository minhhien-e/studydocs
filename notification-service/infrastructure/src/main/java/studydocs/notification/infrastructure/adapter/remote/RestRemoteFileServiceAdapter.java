package studydocs.notification.infrastructure.adapter.remote;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.projection.FileProjection;
import studydocs.notification.application.port.out.remote.RemoteFileServicePort;
import studydocs.notification.infrastructure.dto.integration.FileIntegration;
import studydocs.notification.infrastructure.mapper.FileMapper;
import studydocs.notification.infrastructure.utils.RemoteApiCaller;
import studydocs.notification.shared.web.ApiResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestRemoteFileServiceAdapter implements RemoteFileServicePort {
    private final RemoteApiCaller remoteApiCaller;
    @Value("${app.remote.file-service.url}")
    private String fileServiceUrl;

    @Override
    public FileProjection getById(UUID id) {
        ParameterizedTypeReference<ApiResponse<FileIntegration>> responseType = new ParameterizedTypeReference<>() {
        };
        return FileMapper.toProjection(remoteApiCaller.getForEntity(fileServiceUrl+"/"+id.toString(), responseType));
    }
}
