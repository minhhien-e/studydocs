package gateway.fallback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FallbackRouterConfig {
    @Bean
    public RouterFunction<ServerResponse> fallbackRoutes(FallbackHandler handler) {
        return RouterFunctions.route()
                .GET("/fallback/users", handler::user)
                .GET("/fallback/authentication", handler::authentication)
                .GET("/fallback/notifications", handler::notification)
                .GET("/fallback/review", handler::review)
                .GET("/fallback/follow", handler::follow)
                .GET("/fallback/academic", handler::academic)
//                .GET("/fallback/search", handler::search)
                .GET("/fallback/document", handler::document)
                .GET("/fallback/media", handler::media)
                .build();
    }
}
