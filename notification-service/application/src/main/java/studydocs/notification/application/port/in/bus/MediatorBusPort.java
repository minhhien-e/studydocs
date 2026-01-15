package studydocs.notification.application.port.in.bus;

import studydocs.notification.application.dto.base.Request;

public interface MediatorBusPort {
    // Request / Request
    <R, Q extends Request<R>> R send(Q request);
}
