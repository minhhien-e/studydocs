package studydocs.notificationservice.application.port.mail;

import studydocs.notificationservice.domain.valueobject.EmailData;

public interface EmailSenderPort {
    void send(EmailData emailData);
}
