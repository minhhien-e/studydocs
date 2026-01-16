package studydocs.notification.publisher.otp.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import studydocs.notification.publisher.otp.config.OtpRabbitConfig;
import studydocs.notification.publisher.otp.dto.OtpSentPayload;

@Component
@RequiredArgsConstructor
public class OtpEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishOtpSent(OtpSentPayload payload) {
        rabbitTemplate.convertAndSend(
                OtpRabbitConfig.NOTIFICATION_EXCHANGE,
                OtpRabbitConfig.OTP_SENT_ROUTING_KEY,
                payload
        );
    }
}
