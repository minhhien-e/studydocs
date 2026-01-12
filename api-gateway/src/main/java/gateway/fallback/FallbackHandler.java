package gateway.fallback;

import gateway.enums.FallbackService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class FallbackHandler {

    private final FallbackResponseFactory responseFactory;

    public FallbackHandler(FallbackResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    public Mono<ServerResponse> user(ServerRequest request) {
        return responseFactory.build(FallbackService.USER, request);
    }

    public Mono<ServerResponse> authentication(ServerRequest request) {
        return responseFactory.build(FallbackService.AUTHENTICATION, request);
    }

    public Mono<ServerResponse> notification(ServerRequest request) {
        return responseFactory.build(FallbackService.NOTIFICATION, request);
    }

    public Mono<ServerResponse> review(ServerRequest request) {
        return responseFactory.build(FallbackService.REVIEW, request);
    }

    public Mono<ServerResponse> follow(ServerRequest request) {
        return responseFactory.build(FallbackService.FOLLOW, request);
    }

    public Mono<ServerResponse> academic(ServerRequest request) {
        return responseFactory.build(FallbackService.ACADEMIC, request);
    }

    public Mono<ServerResponse> document(ServerRequest request) {
        return responseFactory.build(FallbackService.DOCUMENT, request);
    }

    public Mono<ServerResponse> media(ServerRequest request) {
        return responseFactory.build(FallbackService.MEDIA, request);
    }
}
