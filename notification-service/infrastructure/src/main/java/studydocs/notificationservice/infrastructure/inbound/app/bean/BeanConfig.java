package studydocs.notificationservice.infrastructure.inbound.app.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studydocs.notificationservice.domain.factory.abstracts.NotificationFactory;
import studydocs.notificationservice.domain.factory.abstracts.RecipientFactory;
import studydocs.notificationservice.domain.factory.abstracts.TemplateFactory;
import studydocs.notificationservice.domain.factory.impl.NotificationFactoryImpl;
import studydocs.notificationservice.domain.factory.impl.RecipientFactoryImpl;
import studydocs.notificationservice.domain.factory.impl.TemplateFactoryImpl;

@Configuration
public class BeanConfig {
    @Bean
    public NotificationFactory notificationFactory() {
        return new NotificationFactoryImpl();
    }

    @Bean
    public RecipientFactory recipientFactory() {
        return new RecipientFactoryImpl();
    }

    @Bean
    public TemplateFactory templateFactory() {
        return new TemplateFactoryImpl();
    }
}
