package studydocs.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewRabbitConfig {

    public static final String NOTIFICATION_EXCHANGE = "notification.exchange";
    public static final String REVIEW_CREATED_ROUTING_KEY = "review.created";
    public static final String DOCUMENT_LIKED_ROUTING_KEY = "document.liked";

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(NOTIFICATION_EXCHANGE);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
