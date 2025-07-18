package studydocs.notificationservice.application.port.ouput.mail;

import studydocs.notificationservice.domain.valueobject.EmailData;

public interface EmailSenderPort {
    void send(EmailData emailData);
}
