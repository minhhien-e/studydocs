package studydocs.notificationservice.infrastructure.inbound.rabbitmq.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notificationservice.application.usecase.email.SendEmailNotificationUseCase;
import studydocs.notificationservice.domain.model.event.SendMailEvent;

@Component
@RequiredArgsConstructor
public class EmailNotificationListener {
    private final SendEmailNotificationUseCase sendEmailNotificationUseCase;

    @RabbitListener(queues = "queue.notification.email")
    public void receive(SendMailEvent event) {
        sendEmailNotificationUseCase.send(event);
    }

}
