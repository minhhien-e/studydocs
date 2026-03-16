package studydocs.notification.infrastructure.adapter.remote;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.projection.UserProjection;
import studydocs.notification.application.port.in.provider.CurrentUserProvider;
import studydocs.notification.application.port.out.remote.RemoteUserServicePort;
import studydocs.notification.domain.exception.AccessDeniedException;
import studydocs.notification.infrastructure.dto.integration.UserIntegration;
import studydocs.notification.infrastructure.mapper.UserMapper;
import studydocs.notification.infrastructure.utils.RemoteApiCaller;
import studydocs.notification.shared.web.ApiResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestRemoteUserServiceAdapter implements RemoteUserServicePort {
    private final RemoteApiCaller remoteApiCaller;
    private final CurrentUserProvider currentUserProvider;
    @Value("${app.remote.user-service.url}")
    private String userServiceUrl;

    @Override
    public UserProjection getById(UUID id) {
        var currentId = currentUserProvider.getCurrentUserId();
        if (!currentId.equals(id)) {
            throw new AccessDeniedException(id, currentId);
        }
        ParameterizedTypeReference<ApiResponse<UserIntegration>> responseType = new ParameterizedTypeReference<>() {
        };
        return UserMapper.toProjection(remoteApiCaller.getForEntity(userServiceUrl, responseType));
    }
}
