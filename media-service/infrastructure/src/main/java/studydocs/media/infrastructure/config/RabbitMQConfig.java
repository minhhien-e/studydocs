package studydocs.media.infrastructure.config;

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
    public static final String UPLOAD_COMPLETED_NOTIFICATION_QUEUE = "upload.completed.notification.queue";
    public static final String UPLOAD_COMPLETED_ROUTING_KEY = "upload.completed";
    public static final String ASSET_CLEANUP_QUEUE = "asset.cleanup.queue";
    public static final String ASSET_UPLOAD_FAILED_ROUTING_KEY = "asset.upload.failed";
    public static final String ASSET_UPLOAD_REQUESTED_QUEUE = "asset.upload.requested.queue";
    public static final String ASSET_UPLOAD_REQUESTED_ROUTING_KEY = "asset.upload.requested";
    public static final String ASSET_ANALYSIS_COMPLETED_QUEUE = "asset.analysis.completed.queue";
    public static final String ASSET_ANALYSIS_COMPLETED_ROUTING_KEY = "asset.analysis.completed";
    public static final String ASSET_DELETION_FAILED_QUEUE = "asset.deletion.failed.queue";
    public static final String ASSET_DELETION_FAILED_ROUTING_KEY = "asset.deletion.failed";

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(NOTIFICATION_EXCHANGE);
    }

    @Bean
    public Queue uploadCompletedNotificationQueue() {
        return new Queue(UPLOAD_COMPLETED_NOTIFICATION_QUEUE);
    }

    @Bean
    public Queue assetCleanupQueue() {
        return new Queue(ASSET_CLEANUP_QUEUE);
    }

    @Bean
    public Queue assetUploadRequestedQueue() {
        return new Queue(ASSET_UPLOAD_REQUESTED_QUEUE);
    }

    @Bean
    public Queue assetAnalysisCompletedQueue() {
        return new Queue(ASSET_ANALYSIS_COMPLETED_QUEUE);
    }

    @Bean
    public Queue assetDeletionFailedQueue() {
        return new Queue(ASSET_DELETION_FAILED_QUEUE);
    }

    @Bean
    public Binding bindUploadCompleted(@Qualifier("uploadCompletedNotificationQueue") Queue notificationUploadedQueue,
            TopicExchange notificationExchange) {
        return BindingBuilder.bind(notificationUploadedQueue).to(notificationExchange)
                .with(UPLOAD_COMPLETED_ROUTING_KEY);
    }

    @Bean
    public Binding bindAssetCleanup(@Qualifier("assetCleanupQueue") Queue assetCleanupQueue,
            TopicExchange notificationExchange) {
        return BindingBuilder.bind(assetCleanupQueue).to(notificationExchange).with(ASSET_UPLOAD_FAILED_ROUTING_KEY);
    }

    @Bean
    public Binding bindAssetUploadRequested(@Qualifier("assetUploadRequestedQueue") Queue queue,
            TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ASSET_UPLOAD_REQUESTED_ROUTING_KEY);
    }

    @Bean
    public Binding bindAssetAnalysisCompleted(@Qualifier("assetAnalysisCompletedQueue") Queue queue,
            TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ASSET_ANALYSIS_COMPLETED_ROUTING_KEY);
    }

    @Bean
    public Binding bindAssetDeletionFailed(@Qualifier("assetDeletionFailedQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ASSET_DELETION_FAILED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
