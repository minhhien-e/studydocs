package studydocs.media.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studydocs.media.domain.policy.FileSupportPolicy;
import studydocs.media.domain.service.DefaultFileSupportPolicy;

@Configuration
public class DomainConfig {
    @Bean
    public FileSupportPolicy fileSupportPolicy() {
        return new DefaultFileSupportPolicy();
    }
}
