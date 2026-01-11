package studydocs.media.api.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import studydocs.media.application.port.in.job.ProcessOutboxUseCase;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxJob {

    private final ProcessOutboxUseCase processOutboxUseCase;

    @Scheduled(fixedDelayString = "${app.outbox.schedule.fixed-delay:5000}")
    public void processOutbox() {
        processOutboxUseCase.processOutbox();
    }
}
