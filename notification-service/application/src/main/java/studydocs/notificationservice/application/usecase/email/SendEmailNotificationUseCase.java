package studydocs.notificationservice.application.usecase.email;

import studydocs.notificationservice.domain.model.event.SendMailEvent;

public interface SendEmailNotificationUseCase {
    void send(SendMailEvent sendMailEvent);
}
