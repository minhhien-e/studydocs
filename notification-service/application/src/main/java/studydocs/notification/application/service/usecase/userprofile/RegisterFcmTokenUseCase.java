package studydocs.notification.application.service.usecase.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.userprofile.RegisterFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.userprofile.RegisterFcmTokenUseCasePort;
import studydocs.notification.domain.aggregate.FcmToken;
import studydocs.notification.domain.exception.userprofile.DuplicateFcmTokenException;
import studydocs.notification.domain.repository.FcmTokenRepository;

@Service
@RequiredArgsConstructor
public class RegisterFcmTokenUseCase implements RegisterFcmTokenUseCasePort {
    private final FcmTokenRepository repository;

    @Override
    public Void execute(RegisterFcmTokenCommand command) {
        if (repository.existsByValue(command.fcmToken()))
            throw new DuplicateFcmTokenException(command.fcmToken());
        var fcmToken = FcmToken.create(command.userId(), command.fcmToken());
        repository.save(fcmToken);
        return null;
    }
}
