package com.example.followerservice.remote.follow.producer;

import com.example.followerservice.remote.follow.PublishNotificationFollowed;
import com.example.followerservice.remote.follow.config.FollowRabbitConfig;
import com.example.followerservice.remote.follow.dto.UserFollowedPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class FollowEventPublisher implements PublishNotificationFollowed {

    private final RabbitTemplate rabbitTemplate;
    @Override
    public void publishUserFollowed(UserFollowedPayload payload) {
        rabbitTemplate.convertAndSend(
                FollowRabbitConfig.NOTIFICATION_EXCHANGE,
                FollowRabbitConfig.USER_FOLLOWED_ROUTING_KEY,
                payload
        );
    }
}
