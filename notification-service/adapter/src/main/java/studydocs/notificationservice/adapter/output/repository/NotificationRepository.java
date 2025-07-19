package studydocs.notificationservice.adapter.output.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.notificationservice.application.port.input.dto.inputmodel.notification.read.GetNotificationByRecipientIdInputModel;
import studydocs.notificationservice.application.port.ouput.repository.NotificationRepositoryPort;
import studydocs.notificationservice.domain.entities.Notification;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationDocument;
import studydocs.notificationservice.infrastructure.mongo.mapper.NotificationMapper;
import studydocs.notificationservice.infrastructure.mongo.repository.NotificationMongoRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepository implements NotificationRepositoryPort {
    private final NotificationMongoRepository repository;

    @Override
    public void save(Notification notification) {
        NotificationDocument document = NotificationMapper.toDocument(notification);
        repository.save(document);
    }
}
