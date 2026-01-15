package studydocs.notification.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String NOTIFICATION_EXCHANGE = "notification.exchange";
    public static final String NOTIFICATION_RECIPIENT_READY_QUEUE = "notification.recipient.ready.queue";
    public static final String NOTIFICATION_RECEIVED_ROUTING_KEY = "notification.recipient.ready";
    public static final String UPLOAD_COMPLETED_NOTIFICATION_QUEUE = "upload.completed.notification.queue";
    public static final String UPLOAD_COMPLETED_ROUTING_KEY = "upload.completed";

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(NOTIFICATION_EXCHANGE);
    }

    @Bean
    public Queue notificationRecipientReadyQueue() {
        return new Queue(NOTIFICATION_RECIPIENT_READY_QUEUE);
    }

    @Bean
    public Queue uploadCompletedNotificationQueue() {
        return new Queue(UPLOAD_COMPLETED_NOTIFICATION_QUEUE);
    }

    @Bean
    public Binding bindNotificationRecipientReady(@Qualifier("notificationRecipientReadyQueue") Queue notificationReceivedQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(notificationReceivedQueue).to(notificationExchange).with(NOTIFICATION_RECEIVED_ROUTING_KEY);
    }

    @Bean
    public Binding bindUploadCompleted(@Qualifier("uploadCompletedNotificationQueue") Queue notificationUploadedQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(notificationUploadedQueue).to(notificationExchange).with(UPLOAD_COMPLETED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
