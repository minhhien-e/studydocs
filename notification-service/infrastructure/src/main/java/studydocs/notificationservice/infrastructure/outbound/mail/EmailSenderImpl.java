package studydocs.notificationservice.infrastructure.outbound.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import studydocs.notificationservice.application.port.mail.EmailSenderPort;
import studydocs.notificationservice.domain.model.valueobject.email.Email;
import studydocs.notificationservice.shared.exception.infrastructure.EmailSendFailureException;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSenderImpl implements EmailSenderPort {
    private final JavaMailSender mailSender;

    @Override
    public void send(Email email) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email.to().value());
            helper.setSubject(email.subject().value());
            helper.setText(email.content().value(), true);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
            throw new EmailSendFailureException();
        }
    }
}
