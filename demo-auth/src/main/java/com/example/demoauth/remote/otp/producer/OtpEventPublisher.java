package com.example.demoauth.remote.otp.producer;

import com.example.demoauth.remote.PublishNotificationEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.example.demoauth.remote.otp.config.OtpRabbitConfig;
import com.example.demoauth.remote.otp.dto.OtpSentPayload;

@Component
@RequiredArgsConstructor
public class OtpEventPublisher  implements PublishNotificationEventPort {

    private final RabbitTemplate rabbitTemplate;

    public void publishOtpSent(OtpSentPayload payload) {
        rabbitTemplate.convertAndSend(
                OtpRabbitConfig.NOTIFICATION_EXCHANGE,
                OtpRabbitConfig.OTP_SENT_ROUTING_KEY,
                payload
        );
    }
}
