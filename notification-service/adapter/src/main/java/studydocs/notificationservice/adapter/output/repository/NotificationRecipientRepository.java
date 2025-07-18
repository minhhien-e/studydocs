package studydocs.notificationservice.adapter.output.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import studydocs.notificationservice.application.port.input.dto.paging.SliceOutput;
import studydocs.notificationservice.application.port.ouput.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.domain.entities.NotificationRecipient;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationRecipientDocument;
import studydocs.notificationservice.infrastructure.mongo.mapper.RecipientMapper;
import studydocs.notificationservice.infrastructure.mongo.repository.NotificationRecipientMongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NotificationRecipientRepository implements NotificationRecipientRepositoryPort {
    private final NotificationRecipientMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    @Override
    public SliceOutput<NotificationRecipient> findByRecipientId(UUID recipientId, LocalDateTime createdAt, int pageNumber, int limit) {
        MatchOperation matchRecipient = Aggregation.match(Criteria.where("recipientId").is(recipientId));
        LookupOperation lookupNotification = LookupOperation.newLookup()
                .from("notification")
                .localField("notificationId")
                .foreignField("id")
                .as("notification");
        UnwindOperation unwindNotification = Aggregation.unwind("notification");
        MatchOperation matchCreatedAt = Aggregation.match(Criteria.where("notification.createdAt").gte(createdAt));
        SortOperation sortByCreatedAt = Aggregation.sort(Sort.Direction.ASC, "notification.createdAt");
        LimitOperation limitOperation = Aggregation.limit(limit + 1);

        Aggregation aggregation = Aggregation.newAggregation(
                matchRecipient,
                lookupNotification,
                unwindNotification,
                matchCreatedAt,
                sortByCreatedAt,
                limitOperation
        );
        AggregationResults<NotificationRecipientDocument> recipients = mongoTemplate
                .aggregate(aggregation, "notification_recipient", NotificationRecipientDocument.class);
        List<NotificationRecipient> recipientDomain = recipients.getMappedResults().stream()
                .map(RecipientMapper::toDomain)
                .toList()
                .subList(0, Math.min(recipients.getMappedResults().size(), limit));
        boolean hasNext = recipientDomain.size() > limit;
        return new SliceOutput<>(recipientDomain, hasNext);
    }
}
