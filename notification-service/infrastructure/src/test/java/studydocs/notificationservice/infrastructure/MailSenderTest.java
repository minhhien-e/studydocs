package studydocs.notificationservice.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@SpringBootTest
public class MailSenderTest {
    @Autowired
    private MailSender mailSender;
    @Test
    public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("minhhien7840@gmail.com");
        message.setSubject("Test");
        message.setText("Test");
        mailSender.send(message);
    }

}
