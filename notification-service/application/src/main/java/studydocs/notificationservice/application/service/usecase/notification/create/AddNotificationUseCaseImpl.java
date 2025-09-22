package studydocs.notificationservice.application.service.usecase.notification.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.notification.create.AddNotificationInput;
import studydocs.notificationservice.application.usecase.notificaton.create.AddNotificationUseCase;
import studydocs.notificationservice.domain.factory.abstracts.NotificationFactory;
import studydocs.notificationservice.domain.repository.NotificationRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class AddNotificationUseCaseImpl implements AddNotificationUseCase {
    private final NotificationRepositoryPort notificationRepositoryPort;
    private final NotificationFactory notificationFactory;

    @Override
    public void execute(AddNotificationInput inputModel) {
        var templateId = inputModel.getTemplateId();
        var senderId = inputModel.getSenderId();
        var category = inputModel.getCategory();
        var templateData = inputModel.getTemplateData();
        notificationRepositoryPort.save(notificationFactory.create(templateId, senderId, category, templateData));
    }
}
