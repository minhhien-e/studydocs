package studydocs.notification.application.service.usecase.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.userprofile.RemoveFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.userprofile.RemoveFcmTokenUseCasePort;
import studydocs.notification.domain.repository.FcmTokenRepository;

@Service
@RequiredArgsConstructor
public class RemoveUserFcmTokenUseCase implements RemoveFcmTokenUseCasePort {
    private final FcmTokenRepository repository;

    @Override
    public Void execute(RemoveFcmTokenCommand command) {
        repository.deleteByValue(command.fcmToken());
        return null;
    }
}
