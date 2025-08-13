package studydocs.notificationservice.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.NotificationTemplateDocument;
import studydocs.notificationservice.infrastructure.outbound.persistence.repository.template.NotificationTemplateMongoRepository;

@SpringBootTest
public class MongoTest {
    @Autowired
    private NotificationTemplateMongoRepository notificationMongoRepository;
    @Test
    public void saveTest(){
        NotificationTemplateDocument document = NotificationTemplateDocument.builder()
                .name("test")
                .channel("EMAIL")
                .subjectTemplate("test")
                .bodyTemplate("test")
                .description("test")
                .build();
        notificationMongoRepository.save(document);
    }
    @Test
    public void findByNameTest(){
        System.out.println(notificationMongoRepository.findByName("REGISTRATION_SUCCESS"));
    }
}
