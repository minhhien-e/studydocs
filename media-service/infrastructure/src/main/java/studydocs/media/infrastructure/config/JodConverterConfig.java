package studydocs.media.infrastructure.config;

import org.jodconverter.core.office.OfficeManager;
import org.jodconverter.local.office.LocalOfficeManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JodConverterConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public OfficeManager officeManager() {
        return LocalOfficeManager.install();
    }
}
