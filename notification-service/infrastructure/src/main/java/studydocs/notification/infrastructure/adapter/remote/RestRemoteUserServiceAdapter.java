package studydocs.notification.infrastructure.adapter.remote;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.view.UserView;
import studydocs.notification.application.port.out.remote.RemoteUserServicePort;
import studydocs.notification.infrastructure.dto.ApiResponse;
import studydocs.notification.infrastructure.utils.RemoteApiCaller;

@Service
@RequiredArgsConstructor
public class RestRemoteUserServiceAdapter implements RemoteUserServicePort {
    private final RemoteApiCaller remoteApiCaller;
    @Value("${app.remote.user-service.url}")
    private String userServiceUrl;

    @Override
    public UserView getById() {
        ParameterizedTypeReference<ApiResponse<UserView>> responseType = new ParameterizedTypeReference<>() {
        };
        return remoteApiCaller.getForEntity(userServiceUrl, responseType);
    }
}
