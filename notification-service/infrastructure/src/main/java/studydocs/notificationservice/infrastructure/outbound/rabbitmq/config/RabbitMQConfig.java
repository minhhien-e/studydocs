package studydocs.notificationservice.infrastructure.outbound.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studydocs.notificationservice.infrastructure.outbound.rabbitmq.properties.RabbitMQProperties;

import java.util.List;

@Configuration
@EnableConfigurationProperties(RabbitMQProperties.class)
@EnableRabbit
public class RabbitMQConfig {
    @Bean
    public Exchange notificationExchange(RabbitMQProperties props) {
        return new TopicExchange(props.getExchange(), true, false);
    }

    @Bean
    public AmqpAdmin amqpAdmin(RabbitTemplate rabbitTemplate, Exchange notificationExchange, RabbitMQProperties props) {
        AmqpAdmin amqpAdmin = new RabbitAdmin(rabbitTemplate);
        amqpAdmin.declareExchange(notificationExchange);
        props.getQueue().forEach(
                queueEntry ->
                        queueEntry.forEach(
                                (queueName, config) ->
                                        declareQueue(amqpAdmin, notificationExchange, config)
                        )

        );
        return amqpAdmin;
    }

    private void declareBindings(AmqpAdmin amqpAdmin, Queue queue, Exchange exchange, List<String> routingKeys) {
        if (routingKeys == null) return;
        routingKeys.forEach(
                routingKey -> {
                    Binding binding = BindingBuilder.bind(queue)
                            .to(exchange)
                            .with(routingKey)
                            .noargs();
                    amqpAdmin.declareBinding(binding);
                }
        );
    }

    private void declareQueue(AmqpAdmin amqpAdmin, Exchange exchange, RabbitMQProperties.QueueConfig config) {
        Queue queue = new Queue(config.getName(), config.isDurable(), config.isExclusive(), config.isAutoDelete());
        amqpAdmin.declareQueue(queue);
        declareBindings(amqpAdmin, queue, exchange, config.getRoutingKey());
    }
}
