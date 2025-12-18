package studydocs.notification.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {

    public static final String NOTIFICATION_EXCHANGE = "notification.exchange";
    public static final String NOTIFICATION_RECEIVED_QUEUE = "notification.received.queue";
    public static final String NOTIFICATION_RECEIVED_ROUTING_KEY = "notification.received.key";

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(NOTIFICATION_EXCHANGE);
    }

    @Bean
    public Queue notificationReceivedQueue() {
        return new Queue(NOTIFICATION_RECEIVED_QUEUE);
    }

    @Bean
    public Binding bindingNotificationReceived(Queue notificationReceivedQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(notificationReceivedQueue).to(notificationExchange).with(NOTIFICATION_RECEIVED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
