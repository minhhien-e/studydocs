package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.query.notification.CountUnreadQuery;
import studydocs.notification.application.port.in.usecase.notification.CountUnreadUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class CountUnreadQueryHandler 
    extends AbstractHandler<CountUnreadQuery, Integer, CountUnreadUseCasePort> {
    
    protected CountUnreadQueryHandler(CountUnreadUseCasePort useCase) {
        super(useCase, CountUnreadQuery.class);
    }
}
