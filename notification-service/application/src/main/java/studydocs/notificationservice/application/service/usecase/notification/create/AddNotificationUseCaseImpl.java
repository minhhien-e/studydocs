package studydocs.notificationservice.application.service.usecase.notification.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.notification.create.AddNotificationInputModel;
import studydocs.notificationservice.application.port.input.usecase.notificaton.create.AddNotificationUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationRepositoryPort;

@Service
@RequiredArgsConstructor
public class AddNotificationUseCaseImpl implements AddNotificationUseCase {
    private final NotificationRepositoryPort notificationRepositoryPort;

    @Override
    public void execute(AddNotificationInputModel inputModel) {
        notificationRepositoryPort.save(inputModel.toDomain());
    }
}
