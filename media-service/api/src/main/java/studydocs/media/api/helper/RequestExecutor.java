package studydocs.media.api.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.media.application.dto.base.Request;
import studydocs.media.application.port.in.bus.MediatorBusPort;
import studydocs.media.application.port.in.provider.CurrentUserProviderPort;

import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class RequestExecutor {
    private final MediatorBusPort mediatorBusPort;
    private final CurrentUserProviderPort currentUserProvider;

    public <R, C> Object execute(Function<R, C> mapper, R request) {
        var command = mapper.apply(request);
        return handleRequest(command);
    }

    public <R, C> Object executeWithCurrentUser(BiFunction<UUID, R, C> mapper, R request) {
        var userId = currentUserProvider.getCurrentUserId();
        var params = mapper.apply(userId, request);
        return handleRequest(params);
    }

    public <R, C, P, V> V executeAndMapView(
            Function<R, C> requestMapper,
            R request,
            Function<P, V> viewMapper) {
        var command = requestMapper.apply(request);
        var result = mediatorBusPort.send((Request<?>) command);
        return mapResult(result, viewMapper);
    }

    public <R, C, P, V> V executeWithCurrentUserAndMapView(
            BiFunction<UUID, R, C> requestMapper,
            R request,
            Function<P, V> viewMapper) {
        var userId = currentUserProvider.getCurrentUserId();
        var command = requestMapper.apply(userId, request);
        var result = mediatorBusPort.send((Request<?>) command);
        return mapResult(result, viewMapper);
    }

    private <C> Object handleRequest(C command) {
        return mediatorBusPort.send((Request<?>) command);
    }

    @SuppressWarnings("unchecked")
    private <P, V> V mapResult(Object result, Function<P, V> viewMapper) {
        if (result == null) {
            return null;
        }

        if (result instanceof List<?> list) {
            return (V) list.stream()
                    .map(item -> viewMapper.apply((P) item))
                    .toList();
        }

        return viewMapper.apply((P) result);
    }
}
