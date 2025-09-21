package studydocs.notificationservice.application.port.mail;

import studydocs.notificationservice.domain.model.valueobject.email.Email;

public interface EmailSenderPort {
    void send(Email emailData);
}
