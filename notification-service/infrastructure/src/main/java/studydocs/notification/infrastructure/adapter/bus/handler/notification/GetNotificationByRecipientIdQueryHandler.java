package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.query.notification.GetNotificationByRecipientIdQuery;
import studydocs.notification.application.dto.readmodel.NotificationRecipientReadModel;
import studydocs.notification.application.port.in.usecase.notification.GetNotificationByRecipientIdUseCasePort;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetNotificationByRecipientIdQueryHandler implements RequestHandler<BusRequestWrapper<GetNotificationByRecipientIdQuery>, List<NotificationRecipientReadModel> > {
    private final GetNotificationByRecipientIdUseCasePort useCase;

    @Override
    public List<NotificationRecipientReadModel> execute(BusRequestWrapper<GetNotificationByRecipientIdQuery> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return GetNotificationByRecipientIdQuery.class;
    }
}
