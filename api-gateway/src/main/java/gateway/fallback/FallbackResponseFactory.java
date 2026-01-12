package gateway.fallback;

import gateway.enums.FallbackService;
import gateway.response.ApiResponse;
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

        ApiResponse<Void> response = ApiResponse.error(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                null,
                traceId
        );

        String message = serviceName.displayName() + " Service is currently unavailable. Please try again later.";

        return ServerResponse
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of(
                        "statusCode", response.statusCode(),
                        "errorCode", response.errorCode() != null ? response.errorCode() : HttpStatus.SERVICE_UNAVAILABLE.value(),
                        "message", message,
                        "traceId", traceId != null ? traceId : "N/A"
                ));
    }


}
