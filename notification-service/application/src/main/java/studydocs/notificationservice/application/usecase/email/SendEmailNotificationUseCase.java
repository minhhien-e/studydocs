package studydocs.notificationservice.application.usecase.email;

import studydocs.notificationservice.domain.event.SendMailEvent;

public interface SendEmailNotificationUseCase {
    void send(SendMailEvent sendMailEvent);
}
