package studydocs.notificationservice.application.port.input.email;

import studydocs.notificationservice.domain.event.SendMailEvent;

public interface SendEmailNotificationUseCase {
    void send(SendMailEvent sendMailEvent);
}
