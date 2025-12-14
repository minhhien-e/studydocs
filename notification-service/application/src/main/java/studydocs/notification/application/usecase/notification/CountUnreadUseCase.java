package studydocs.notification.application.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.query.notification.CountUnreadQuery;
import studydocs.notification.application.port.in.usecase.notification.CountUnreadUseCasePort;
import studydocs.notification.application.port.out.repository.NotificationRecipientQueries;

@Service
@RequiredArgsConstructor
public class CountUnreadUseCase implements CountUnreadUseCasePort {
    private final NotificationRecipientQueries notificationRepository;
    @Override
    public Integer execute(CountUnreadQuery params) {
        return notificationRepository.countUnread(params.recipientId());
    }
}
