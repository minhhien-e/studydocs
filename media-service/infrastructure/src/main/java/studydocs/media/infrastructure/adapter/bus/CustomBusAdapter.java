package studydocs.media.infrastructure.adapter.bus;

import io.github.mediatR.api.Bus;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import studydocs.media.application.dto.base.Request;
import studydocs.media.application.exception.ApplicationDomainException;
import studydocs.media.application.port.in.bus.MediatorBusPort;

import studydocs.media.domain.exception.base.DomainException;

@Component
@RequiredArgsConstructor
@Transactional
public class CustomBusAdapter implements MediatorBusPort {
    private final Bus bus;

    @Override
    public <R, Q extends Request<R>> R send(Q request) {
        try {
            return bus.send(BusRequestWrapper.of(request));
        } catch (DomainException e) {
             throw new ApplicationDomainException(
                 e.getMessage(), 
                 e.getErrorCode().getValue(), 
                 e.getErrorCode().getCategory().name()
             );
        }
    }
}
