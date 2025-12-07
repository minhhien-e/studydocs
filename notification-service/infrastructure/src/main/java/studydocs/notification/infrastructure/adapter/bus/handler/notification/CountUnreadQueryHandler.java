package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.query.notification.CountUnreadQuery;
import studydocs.notification.application.port.in.usecase.notification.CountUnreadUseCasePort;

@Component
@RequiredArgsConstructor
public class CountUnreadQueryHandler implements RequestHandler<BusRequestWrapper<CountUnreadQuery>, Integer> {
    private final CountUnreadUseCasePort useCase;

    @Override
    public Integer execute(BusRequestWrapper<CountUnreadQuery> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return CountUnreadQuery.class;
    }
}
