package gateway.fallback;

import gateway.enums.FallbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class FallbackResponseFactory {

    public Mono<ServerResponse> build(FallbackService serviceName, ServerRequest request) {
        String traceId = request.headers().firstHeader("X-Trace-Id");
        String message = serviceName.displayName() + " Service is currently unavailable. Please try again later.";

        return ServerResponse
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(gateway.response.ApiResponse.error(
                        HttpStatus.SERVICE_UNAVAILABLE.value(),
                        HttpStatus.SERVICE_UNAVAILABLE.value(),
                        traceId != null ? traceId : "N/A"
                ));
    }


}
