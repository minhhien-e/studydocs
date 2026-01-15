package studydocs.media.api.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import studydocs.media.application.annotation.DistributableJobLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import studydocs.media.application.port.in.job.ProcessOutboxUseCase;

@Component
@RequiredArgsConstructor
public class OutboxJob {

    private final ProcessOutboxUseCase processOutboxUseCase;

    @Value("${app.outbox.batch-size:20}")
    private int batchSize;

    @Scheduled(fixedDelayString = "${app.outbox.schedule.fixed-delay:5000}")
    @DistributableJobLock(name = "outbox.process.pending-events", lockAtMostFor = 600, lockAtLeastFor = 5)
    public void processOutbox() {
        processOutboxUseCase.processOutbox(batchSize);
    }
}
