package studydocs.notificationservice.application.service.usecase.notification.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.dto.input.notification.create.AddNotificationInput;
import studydocs.notificationservice.application.usecase.notificaton.create.AddNotificationUseCase;
import studydocs.notificationservice.domain.repository.NotificationRepositoryPort;

@Service
@RequiredArgsConstructor
public class AddNotificationUseCaseImpl implements AddNotificationUseCase {
    private final NotificationRepositoryPort notificationRepositoryPort;

    @Override
    public void execute(AddNotificationInput inputModel) {
        notificationRepositoryPort.save(inputModel.toDomain());
    }
}
