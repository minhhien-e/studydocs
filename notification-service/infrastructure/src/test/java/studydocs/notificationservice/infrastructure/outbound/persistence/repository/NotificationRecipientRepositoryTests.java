package studydocs.notificationservice.infrastructure.outbound.persistence.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import studydocs.notificationservice.shared.paging.SliceOutput;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.domain.repository.NotificationRepositoryPort;
import studydocs.notificationservice.domain.entity.Notification;
import studydocs.notificationservice.domain.entity.NotificationRecipient;
import studydocs.notificationservice.shared.enums.NotificationType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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
    @Autowired
    private NotificationRepositoryPort notificationRepository;

    @Test
    void hasAnyUnread_WhenUnreadExists_ReturnsTrue() {
    }

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
        NotificationRecipient otherRecipient = new NotificationRecipient(UUID.randomUUID(), UUID.randomUUID());
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

    @Test
    void findByRecipientId_WhenDataValid_ReturnsPaging() {
        // Arrange
        UUID recipientId = UUID.randomUUID();
        int limit = 3;

        // Tạo 5 notifications với thời gian createdAt tăng dần
        for (int i = 0; i < 5; i++) {
            // Tạo Notification
            Map<String, Object> templateData = Map.of("name", "name" + i);
            Notification notification = new Notification(UUID.randomUUID(), UUID.randomUUID(), NotificationType.NEW_DOCUMENT.name(), templateData);
            NotificationRecipient recipient = new NotificationRecipient(recipientId, notification.getId());
            repository.save(recipient);
            notificationRepository.save(notification);
        }

        LocalDateTime cutoff = LocalDateTime.now(); // Thời gian hiện tại làm mốc

        // Act
        SliceOutput<NotificationRecipient> result = repository.findByRecipientId(recipientId, cutoff, limit);

        // Assert
        assertEquals(limit, result.content().size()); // Chỉ lấy đúng số lượng limit
        assertTrue(result.hasNext()); // Vì có 5 bản ghi nhưng limit = 3

        // Kiểm tra thứ tự createdAt tăng dần
        List<LocalDateTime> createdAts = result.content().stream()
                .map(recipient -> recipient.getNotification().getCreateAt().getValue()) // hoặc recipient.getNotification().getCreatedAt() tùy cấu trúc
                .toList();

        for (int i = 1; i < createdAts.size(); i++) {
            assert createdAts.get(i - 1).isBefore(createdAts.get(i)) ||
                    createdAts.get(i - 1).isEqual(createdAts.get(i));
        }
    }

}
