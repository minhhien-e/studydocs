package studydocs.media.infrastructure.adapter.bus;

import io.github.mediatR.api.Bus;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import studydocs.media.application.dto.base.Request;
import studydocs.media.application.port.in.bus.MediatorBusPort;
import studydocs.media.domain.exception.base.DomainException;
import studydocs.media.infrastructure.adapter.web.exception.DomainToHttpExceptionMapper;

@Component
@RequiredArgsConstructor
@Transactional
public class CustomBusAdapter implements MediatorBusPort {
    private final Bus bus;
    private final DomainToHttpExceptionMapper domainToHttpExceptionMapper;

    @Override
    public <R, Q extends Request<R>> R send(Q request) {
        try {
            return bus.send(BusRequestWrapper.of(request));
        } catch (DomainException e) {
            throw domainToHttpExceptionMapper.map(e);
        }
    }
}
