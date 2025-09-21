package studydocs.notificationservice.infrastructure.outbound.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import studydocs.notificationservice.application.port.mail.EmailSenderPort;
import studydocs.notificationservice.domain.model.valueobject.email.Email;
import studydocs.notificationservice.shared.exception.concrete.mail.EmailSendFailureException;

@Component
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSenderPort {
    private final JavaMailSender mailSender;

    @Override
    public void send(Email emailData) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailData.to());
            helper.setSubject(emailData.subject());
            helper.setText(emailData.text(), true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new EmailSendFailureException(e.getMessage());
        }
    }
}
