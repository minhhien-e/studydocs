package studydocs.notificationservice.infrastructure.inbound.scheduling;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import studydocs.notificationservice.application.usecase.notificaton.delete.CleanOldNotificationsUseCase;

@Component
@RequiredArgsConstructor
public class CleanNotificationJob {
    private final CleanOldNotificationsUseCase cleanOldNotificationsUseCase;

    @Scheduled(cron = "0 0 0 * * ?")
    public void clean() {
        cleanOldNotificationsUseCase.execute();
    }
}
