package studydocs.notification.application.service.usecase.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.command.userprofile.RegisterFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.userprofile.RegisterFcmTokenUseCasePort;
import studydocs.notification.domain.repository.UserNotificationProfileRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterFcmTokenUseCase implements RegisterFcmTokenUseCasePort {
    private final UserNotificationProfileRepository repository;

    @Override
    public Void execute(RegisterFcmTokenCommand command) {
        var profile = repository.getByUserId(command.userId());
        profile.registerFcmToken(command.fcmToken());
        repository.save(profile);
        return null;
    }
}
