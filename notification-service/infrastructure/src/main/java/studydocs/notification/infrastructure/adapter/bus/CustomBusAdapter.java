package studydocs.notification.infrastructure.adapter.bus;

import io.github.mediatR.api.Bus;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.base.Request;
import studydocs.notification.application.port.in.bus.MediatorBusPort;
import studydocs.notification.domain.exception.base.DomainException;
import studydocs.notification.infrastructure.exception.mapper.DomainToHttpExceptionMapper;

@Component
@RequiredArgsConstructor
@Transactional
public class CustomBusAdapter implements MediatorBusPort {
    private final DomainToHttpExceptionMapper exceptionMapper;
    private final Bus bus;

    @Override
    public <R, Q extends Request<R>> R send(Q request) {
        try {
            return bus.send(BusRequestWrapper.of(request));
        } catch (DomainException e) {
            throw exceptionMapper.map(e);
        }
    }
}
