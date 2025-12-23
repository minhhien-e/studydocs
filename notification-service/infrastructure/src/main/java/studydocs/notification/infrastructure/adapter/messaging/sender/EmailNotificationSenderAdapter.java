package studydocs.notification.infrastructure.adapter.messaging.sender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.payload.NotificationSendPayload;
import studydocs.notification.application.port.out.messaging.NotificationSenderPort;
import studydocs.notification.infrastructure.config.email.EmailProperties;
import studydocs.notification.infrastructure.exception.SendMailFailedException;

@Component
@RequiredArgsConstructor
public class EmailNotificationSenderAdapter implements NotificationSenderPort {
    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;

    @Override
    public void send(NotificationSendPayload payload) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailProperties.getFrom());
            helper.setTo(payload.destinations().toArray(new String[0]));
            helper.setSubject(payload.subject());

            helper.setText(payload.body(), true);

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new SendMailFailedException(e.getMessage());
        }
    }
}
