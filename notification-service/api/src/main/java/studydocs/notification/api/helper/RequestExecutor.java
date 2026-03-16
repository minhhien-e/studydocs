package studydocs.notification.api.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.base.CursorPaginationResult;
import studydocs.notification.application.dto.base.Request;
import studydocs.notification.application.port.in.bus.MediatorBusPort;
import studydocs.notification.application.port.in.provider.CurrentTraceIdProvider;
import studydocs.notification.application.port.in.provider.CurrentUserProvider;
import studydocs.notification.shared.web.ApiResponse;

import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class RequestExecutor {
    private final MediatorBusPort mediatorBusPort;
    private final CurrentUserProvider currentUserProvider;
    private final CurrentTraceIdProvider traceIdProvider;

    public <R, C> ResponseEntity<ApiResponse<?>> execute(Function<R, C> mapper, R request, HttpStatus status) {
        var command = mapper.apply(request);
        return handleRequest(command, status);
    }

    // BusRequest có userId
    public <R, C> ResponseEntity<ApiResponse<?>> executeWithCurrentUser(BiFunction<UUID, R, C> mapper, R request, HttpStatus status) {
        var userId = currentUserProvider.getCurrentUserId();
        var params = mapper.apply(userId, request);
        return handleRequest(params, status);
    }
    
    public <R, C, P, V> ResponseEntity<ApiResponse<?>> executeAndMapView(
            Function<R, C> requestMapper,
            R request,
            Function<P, V> viewMapper,
            HttpStatus status
    ) {
        var command = requestMapper.apply(request);
        var result = mediatorBusPort.send((Request<?>) command);
        var mappedResult = mapResult(result, viewMapper);
        return ResponseEntity.status(status).body(
            ApiResponse.success(status.value(), mappedResult, traceIdProvider.getCurrentTraceId())
        );
    }
    
    public <R, C, P, V> ResponseEntity<ApiResponse<?>> executeWithCurrentUserAndMapView(
            BiFunction<UUID, R, C> requestMapper,
            R request,
            Function<P, V> viewMapper,
            HttpStatus status
    ) {
        var userId = currentUserProvider.getCurrentUserId();
        var command = requestMapper.apply(userId, request);
        var result = mediatorBusPort.send((Request<?>) command);
        var mappedResult = mapResult(result, viewMapper);
        return ResponseEntity.status(status).body(
            ApiResponse.success(status.value(), mappedResult, traceIdProvider.getCurrentTraceId())
        );
    }

    private <C> ResponseEntity<ApiResponse<?>> handleRequest(C command, HttpStatus status) {
        var result = mediatorBusPort.send((Request<?>) command);
        return ResponseEntity.status(status).body(ApiResponse.success(status.value(), result, traceIdProvider.getCurrentTraceId()));
    }
    
    @SuppressWarnings("unchecked")
    private <P, V> Object mapResult(Object result, Function<P, V> viewMapper) {
        if (result == null) {
            return null;
        }
        
        if (result instanceof List<?> list) {
            return list.stream()
                .map(item -> viewMapper.apply((P) item))
                .toList();
        }
        
        if (result instanceof CursorPaginationResult<?> paginationResult) {
            var mappedData = paginationResult.data().stream()
                .map(item -> viewMapper.apply((P) item))
                .toList();
            
            return new CursorPaginationResult<>(
                mappedData,
                paginationResult.nextCursor(),
                paginationResult.total(),
                paginationResult.hasNext()
            );
        }
        
        return viewMapper.apply((P) result);
    }
}
