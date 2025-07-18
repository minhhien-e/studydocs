package studydocs.notificationservice.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailNotificationListenerTest {
    @Autowired
    private MailProperties mailProperties;

    @Test
    void receive() {
        System.out.println(mailProperties.getPassword());
        System.out.println(mailProperties.getUsername());

    }
}
