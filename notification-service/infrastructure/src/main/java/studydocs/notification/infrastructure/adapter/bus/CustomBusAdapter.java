package studydocs.notification.infrastructure.adapter.bus;

import io.github.mediatR.api.Bus;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.base.Request;
import studydocs.notification.application.port.in.bus.MediatorBusPort;
import studydocs.notification.domain.exception.base.DomainException;
import studydocs.notification.infrastructure.adapter.web.exception.DomainToHttpExceptionMapper;
import studydocs.notification.infrastructure.adapter.web.exception.InfrastructureToHttpExceptionMapper;
import studydocs.notification.infrastructure.exception.base.InfrastructureException;

@Component
@RequiredArgsConstructor
public class CustomBusAdapter implements MediatorBusPort {
    private final DomainToHttpExceptionMapper domainToHttpExceptionMapper;
    private final InfrastructureToHttpExceptionMapper infrastructureToHttpExceptionMapper;
    private final Bus bus;

    @Override
    public <R, Q extends Request<R>> R send(Q request) {
        try {
            return bus.send(BusRequestWrapper.of(request));
        } catch (DomainException e) {
            throw domainToHttpExceptionMapper.map(e);
        }
        catch (InfrastructureException e){
            throw infrastructureToHttpExceptionMapper.map(e);
        }
    }
}
