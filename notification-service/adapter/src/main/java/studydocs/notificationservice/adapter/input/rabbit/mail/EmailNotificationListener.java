package studydocs.notificationservice.adapter.input.rabbit.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notificationservice.application.port.input.email.SendEmailNotificationUseCase;
import studydocs.notificationservice.domain.event.SendMailEvent;

@Component
@RequiredArgsConstructor
public class EmailNotificationListener {
    private final SendEmailNotificationUseCase sendEmailNotificationUseCase;

    @RabbitListener(queues = "queue.notification.email")
    public void receive(SendMailEvent event) {
        sendEmailNotificationUseCase.send(event);
    }

}
