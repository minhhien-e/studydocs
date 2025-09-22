package studydocs.notificationservice.infrastructure.outbound.persistence.repository.recipient.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import studydocs.notificationservice.application.dto.output.UserNotificationDto;
import studydocs.notificationservice.application.port.repository.RecipientReadRepositoryPort;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.RecipientDocument;
import studydocs.notificationservice.infrastructure.outbound.persistence.mapper.RecipientMapper;
import studydocs.notificationservice.shared.paging.SliceOutput;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@RequiredArgsConstructor
public class RecipientReadRepositoryAdapter implements RecipientReadRepositoryPort {
    private final MongoTemplate mongoTemplate;

    @Override
    public SliceOutput<UserNotificationDto> findByRecipientId(UUID recipientId, boolean isDeleted, LocalDateTime createdAt, int limit) {
        Criteria criteria = Criteria.where("recipientId").is(recipientId);

        if (isDeleted) {
            criteria = criteria.and("deletedAt").ne(null);
        } else {
            criteria = criteria.and("deletedAt").is(null);
        }

        MatchOperation matchRecipient = Aggregation.match(criteria);
        LookupOperation lookupNotification = LookupOperation.newLookup().from("notification").localField("notificationId").foreignField("_id").as("notification");
        UnwindOperation unwindNotification = Aggregation.unwind("notification");
        MatchOperation matchCreatedAt = Aggregation.match(where("notification.creationTime").lte(createdAt));

        SortOperation sortByCreatedAt = Aggregation.sort(Sort.Direction.ASC, "notification.creationTime");
        LimitOperation limitOperation = Aggregation.limit(limit + 1);

        Aggregation aggregation = Aggregation.newAggregation(matchRecipient, lookupNotification, unwindNotification, matchCreatedAt, sortByCreatedAt, limitOperation);
        AggregationResults<RecipientDocument> recipients = mongoTemplate.aggregate(aggregation, "notification_recipient", RecipientDocument.class);
        List<UserNotificationDto> userNotificationDtoS = recipients.getMappedResults().stream().map(recipient -> RecipientMapper.toDto(recipient.getNotification(), recipient)).toList();

        boolean hasNext = userNotificationDtoS.size() > limit;

        if (hasNext) userNotificationDtoS = userNotificationDtoS.subList(0, limit);

        return new SliceOutput<>(userNotificationDtoS, hasNext);
    }
}
