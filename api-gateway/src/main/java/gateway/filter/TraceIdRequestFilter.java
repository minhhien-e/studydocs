package gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@Component
public class TraceIdRequestFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(TraceIdRequestFilter.class);
    private static final String TRACE_ID_HEADER = "X-Trace-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // =========================
        // 1. Generate / Get TraceId
        // =========================
        String traceId = exchange.getRequest()
                .getHeaders()
                .getFirst(TRACE_ID_HEADER);

        if (traceId == null || traceId.isBlank()) {
            traceId = UUID.randomUUID().toString();
        }

        String finalTraceId = traceId;

        // =========================
        // 2. Log incoming request
        // =========================
        log.info(
                "[TraceID: {}] ➡️ Incoming request: {} {}",
                finalTraceId,
                exchange.getRequest().getMethod(),
                exchange.getRequest().getURI()
        );

        // =========================
        // 3. Add TraceId to header
        // =========================
        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(request -> request.headers(headers ->
                        headers.set(TRACE_ID_HEADER, finalTraceId)
                ))
                .build();

        // =========================
        // 4. Continue filter chain
        // =========================
        return chain.filter(mutatedExchange)
                .doOnSuccess(aVoid -> logRoutedRequest(mutatedExchange, finalTraceId))
                .doOnError(error -> logError(mutatedExchange, finalTraceId, error));
    }

    /**
     * Log request AFTER gateway routing
     */
    private void logRoutedRequest(ServerWebExchange exchange, String traceId) {

        URI routedUri = exchange.getAttribute(
                ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR
        );

        HttpStatusCode status = exchange.getResponse().getStatusCode();

        if (routedUri != null) {
            log.info(
                    "[TraceID: {}] 🔀 Routed to service: {} {} -> {} | status={}",
                    traceId,
                    exchange.getRequest().getMethod(),
                    exchange.getRequest().getPath(),
                    routedUri,
                    status != null ? status.value() : "N/A"
            );
        } else {
            log.warn(
                    "[TraceID: {}] ⚠️ Routed URI not found | path={}",
                    traceId,
                    exchange.getRequest().getPath()
            );
        }
    }

    /**
     * Log error case
     */
    private void logError(ServerWebExchange exchange, String traceId, Throwable error) {
        log.error(
                "[TraceID: {}] ❌ Request failed: {} {} | error={}",
                traceId,
                exchange.getRequest().getMethod(),
                exchange.getRequest().getURI(),
                error.getMessage(),
                error
        );
    }

    @Override
    public int getOrder() {
        // Chạy sớm để các filter sau vẫn có TraceId
        return -100;
    }
}
