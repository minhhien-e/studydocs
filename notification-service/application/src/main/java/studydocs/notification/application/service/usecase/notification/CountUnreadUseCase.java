package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.query.notification.CountUnreadQuery;
import studydocs.notification.application.port.in.usecase.notification.CountUnreadUseCasePort;
import studydocs.notification.application.port.out.repository.NotificationRecipientQueries;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CountUnreadUseCase implements CountUnreadUseCasePort {
    private final NotificationRecipientQueries notificationRepository;
    @Override
    public Integer execute(CountUnreadQuery params) {
        return notificationRepository.countUnread(params.recipientId());
    }
}
