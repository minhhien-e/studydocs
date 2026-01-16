package studydocs.notification.publisher.follow.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import studydocs.notification.publisher.follow.config.FollowRabbitConfig;
import studydocs.notification.publisher.follow.dto.UserFollowedPayload;

@Component
@RequiredArgsConstructor
public class FollowEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishUserFollowed(UserFollowedPayload payload) {
        rabbitTemplate.convertAndSend(
                FollowRabbitConfig.NOTIFICATION_EXCHANGE,
                FollowRabbitConfig.USER_FOLLOWED_ROUTING_KEY,
                payload
        );
    }
}
