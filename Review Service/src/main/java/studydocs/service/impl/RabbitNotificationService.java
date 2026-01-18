package studydocs.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import studydocs.config.dto.ReviewCreatedPayload;
import studydocs.config.ReviewRabbitConfig;
import studydocs.service.NotificationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitNotificationService implements NotificationService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendReviewCreated(ReviewCreatedPayload payload) {
        try {
            rabbitTemplate.convertAndSend(
                    ReviewRabbitConfig.NOTIFICATION_EXCHANGE,
                    ReviewRabbitConfig.REVIEW_CREATED_ROUTING_KEY,
                    payload);
            log.info("Published ReviewCreated event for reviewId: {}", payload.reviewId());
        } catch (Exception e) {
            log.error("Failed to publish ReviewCreated event", e);
        }
    }

    @Override
    public void sendDocumentLiked(studydocs.config.dto.DocumentLikedPayload payload) {
        try {
            rabbitTemplate.convertAndSend(
                    ReviewRabbitConfig.NOTIFICATION_EXCHANGE,
                    ReviewRabbitConfig.DOCUMENT_LIKED_ROUTING_KEY,
                    payload);
            log.info("Published DocumentLiked event for docId: {}", payload.documentId());
        } catch (Exception e) {
            log.error("Failed to publish DocumentLiked event", e);
        }
    }
}
