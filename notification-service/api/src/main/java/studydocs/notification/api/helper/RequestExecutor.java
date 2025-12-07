package studydocs.notification.api.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import studydocs.notification.api.dto.response.ApiResponse;
import studydocs.notification.application.dto.base.Request;
import studydocs.notification.application.port.in.bus.MediatorBusPort;
import studydocs.notification.application.port.in.provider.CurrentTraceIdProvider;
import studydocs.notification.application.port.in.provider.CurrentUserProviderPort;

import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class RequestExecutor {
    private final MediatorBusPort mediatorBusPort;
    private final CurrentUserProviderPort currentUserProviderPort;
    private final CurrentTraceIdProvider traceIdProvider;

    public <R, C> ResponseEntity<ApiResponse<?>> execute(Function<R, C> mapper, R request, HttpStatus status) {
        var command = mapper.apply(request);
        return handleRequest(command, status);
    }

    // BusRequest có userId
    public <R, C> ResponseEntity<ApiResponse<?>> executeWithCurrentUser(BiFunction<UUID, R, C> mapper, R request, HttpStatus status) {
        var userId = currentUserProviderPort.getCurrentUserId();
        var params = mapper.apply(userId, request);
        return handleRequest(params, status);
    }

    private <C> ResponseEntity<ApiResponse<?>> handleRequest(C command, HttpStatus status) {
        var result = mediatorBusPort.send((Request<?>) command);
        return ResponseEntity.status(status).body(ApiResponse.success(status.value(), result, traceIdProvider.getCurrentTraceId()));
    }
}
