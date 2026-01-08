package studydocs.media.infrastructure.adapter.provider;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import studydocs.media.application.port.in.provider.CurrentTraceIdProvider;

@Component
public class MdcTraceIdProvider implements CurrentTraceIdProvider {
    @Override
    public String getCurrentTraceId() {
        return MDC.get("traceId");
    }
}
