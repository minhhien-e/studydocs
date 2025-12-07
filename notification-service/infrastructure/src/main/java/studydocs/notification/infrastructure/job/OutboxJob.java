package studydocs.notification.infrastructure.job;

import io.github.infrastructure.mongo.entity.OutboxEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import studydocs.notification.application.port.out.messaging.NotificationMessagePort;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxJob {

    private final MongoTemplate mongoTemplate;
    private final NotificationMessagePort notificationMessagePort;

    @Scheduled(fixedDelay = 5000)
    public void processOutbox() {
        // 1. Fetch pending events
        Query query = new Query(Criteria.where("status").is("PENDING"));
        query.limit(20); // Batch size

        List<OutboxEntity> pendingEvents = mongoTemplate.find(query, OutboxEntity.class);

        for (OutboxEntity event : pendingEvents) {
            try {
                // 2. Lock & Mark as Processing (Optimistic locking or status upgrade)
                Query lockQuery = new Query(Criteria.where("id").is(event.getId()).and("status").is("PENDING"));
                Update lockUpdate = new Update().set("status", "PROCESSING");
                OutboxEntity lockedEvent = mongoTemplate.findAndModify(
                        lockQuery,
                        lockUpdate,
                        new FindAndModifyOptions().returnNew(true),
                        OutboxEntity.class
                );

                if (lockedEvent != null) {
                    processEvent(lockedEvent);
                }
            } catch (Exception e) {
                log.error("Failed to process outbox event: {}", event.getId(), e);
            }
        }
    }

    private void processEvent(OutboxEntity event) {
        try {
            log.info("Processing Outbox Event: ID={}, Type={}", event.getId(), event.getType());
            
            if (event.getPayload() instanceof studydocs.notification.domain.event.NotificationReceivedEvent) {
                notificationMessagePort.publish((studydocs.notification.domain.event.NotificationReceivedEvent) event.getPayload());
            } else {
                log.warn("Unknown event type: {}", event.getType());
            }
            
            // 3. Mark as Processed
            Query query = new Query(Criteria.where("id").is(event.getId()));
            Update update = new Update().set("status", "PROCESSED");
            mongoTemplate.updateFirst(query, update, OutboxEntity.class);
            
        } catch (Exception e) {
            log.error("Error processing event payload", e);
            Query query = new Query(Criteria.where("id").is(event.getId()));
            Update update = new Update().set("status", "FAILED");
            mongoTemplate.updateFirst(query, update, OutboxEntity.class);
        }
    }
}
