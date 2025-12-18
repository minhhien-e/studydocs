package studydocs.notification.application.service.usecase.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.userprofile.RemoveFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.userprofile.RemoveFcmTokenUseCasePort;
import studydocs.notification.domain.repository.UserNotificationProfileRepository;

@Service
@RequiredArgsConstructor
public class RemoveFcmTokenUseCase implements RemoveFcmTokenUseCasePort {
    private final UserNotificationProfileRepository repository;

    @Override
    public Void execute(RemoveFcmTokenCommand command) {
        var profile = repository.getByUserId(command.userId());
        profile.removeFcmToken(command.fcmToken());
        repository.save(profile);
        return null;
    }
}
