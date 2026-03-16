package studydocs.notification.infrastructure.adapter.provider;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import studydocs.notification.application.port.in.provider.CurrentTraceIdProvider;

@Component
public class MdcTraceIdProvider  implements CurrentTraceIdProvider {
    public String getCurrentTraceId() {
        return MDC.get("traceId");
    }
}
