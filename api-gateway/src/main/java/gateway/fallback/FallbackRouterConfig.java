package gateway.fallback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FallbackRouterConfig {
    @Bean
    public RouterFunction<ServerResponse> fallbackRoutes(FallbackHandler handler) {
        return RouterFunctions.route()
                .route(RequestPredicates.path("/fallback/users"),
                        handler::user)
                .route(RequestPredicates
                        .path("/fallback/authentication"), handler::authentication)
                .route(RequestPredicates
                        .path("/fallback/notifications"), handler::notification)
                .route(RequestPredicates.path("/fallback/review"),
                        handler::review)
                .route(RequestPredicates.path("/fallback/follow"),
                        handler::follow)
                .route(RequestPredicates.path("/fallback/academic"),
                        handler::academic)
                // .route(RequestPredicates.path("/fallback/search"),
                // handler::search)
                .route(RequestPredicates.path("/fallback/document"),
                        handler::document)
                .route(RequestPredicates.path("/fallback/media"),
                        handler::media)
                .build();
    }
}
