package gateway.config;

import gateway.exception.GlobalErrorWebExceptionHandler;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;

@Configuration
public class ExceptionHandlerConfig {

    @Bean
    @Order(-2)
    public GlobalErrorWebExceptionHandler globalErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                                                       WebProperties resources,
                                                                       ApplicationContext applicationContext,
                                                                       ServerCodecConfigurer serverCodecConfigurer) {
        return new GlobalErrorWebExceptionHandler(errorAttributes, resources.getResources(), applicationContext, serverCodecConfigurer);
    }
}
