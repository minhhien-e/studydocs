package studydocs.notificationservice.adapter.output.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import studydocs.notificationservice.application.port.ouput.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.domain.entities.NotificationRecipient;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Testcontainers
public class NotificationRecipientRepositoryTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private NotificationRecipientRepositoryPort repository;

    @Test
    void hasAnyUnread_WhenNoUnreadExists_ReturnsFalse() {
        // Arrange
        UUID recipientId = UUID.randomUUID();
        UUID notificationId = UUID.randomUUID();
        NotificationRecipient recipient = new NotificationRecipient(recipientId, notificationId);
        recipient.read();
        repository.save(recipient);

        // Act
        boolean result = repository.hasAnyUnread(recipientId);
        // Assert
        assertFalse(result);
    }

    @Test
    void markAllAsRead_WhenUnreadNotificationsExist_ReturnsCorrectCount() {
        // Arrange
        UUID recipientId = UUID.randomUUID();
        NotificationRecipient unread1 = new NotificationRecipient(recipientId, UUID.randomUUID());
        NotificationRecipient unread2 = new NotificationRecipient(recipientId, UUID.randomUUID());
        NotificationRecipient otherRecipient = new NotificationRecipient( UUID.randomUUID(), UUID.randomUUID());
        NotificationRecipient deleted = new NotificationRecipient(recipientId, UUID.randomUUID());
        deleted.delete();
        repository.save(unread1);
        repository.save(unread2);
        repository.save(deleted);
        repository.save(otherRecipient);
        // Act
        long modifiedCount = repository.markAllAsRead(recipientId);
        // Assert
        assertEquals(2, modifiedCount);
    }

    @Test
    void markAllAsRead_WhenNotificationsAreDeleted_ReturnsZero() {
        // Arrange
        UUID recipientId = UUID.randomUUID();
        NotificationRecipient deleted1 = new NotificationRecipient(recipientId, UUID.randomUUID());
        NotificationRecipient deleted2 = new NotificationRecipient(recipientId, UUID.randomUUID());
        NotificationRecipient deleted3 = new NotificationRecipient(recipientId, UUID.randomUUID());
        deleted1.delete();
        deleted2.delete();
        deleted3.delete();
        repository.save(deleted1);
        repository.save(deleted2);
        repository.save(deleted3);
        // Act
        long modifiedCount = repository.markAllAsRead(recipientId);
        // Assert
        assertEquals(0, modifiedCount);
    }
}
