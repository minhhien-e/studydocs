package studydocs.media.application.port.in.bus;

import studydocs.media.application.dto.base.Request;

public interface MediatorBusPort {
    <R, Q extends Request<R>> R send(Q request);
}
