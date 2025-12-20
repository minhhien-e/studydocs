
package studydocs.notification.infrastructure.adapter.repository.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import studydocs.notification.application.dto.projection.UserNotificationProfileProjection;
import studydocs.notification.application.port.out.repository.UserNotificationProfileQueries;
import studydocs.notification.domain.exception.userprofile.UserNotificationProfileNotFoundException;
import studydocs.notification.infrastructure.persistence.entity.UserNotificationProfileEntity;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserNotificationProfileQueryAdapter implements UserNotificationProfileQueries {
    private final MongoTemplate mongoTemplate;

    @Override
    public UserNotificationProfileProjection getByUserId(UUID userId) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("userId").is(userId));
        LookupOperation lookupOperation = Aggregation.lookup("fcm_tokens", "userId", "userId", "fcm_tokens");
        UnwindOperation unwindOperation = Aggregation.unwind("fcm_tokens");
        GroupOperation groupOperation = Aggregation.group("userId")
                .push("fcm_tokens.value").as("fcmTokens");
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, lookupOperation, unwindOperation, groupOperation);

        var results = mongoTemplate.aggregate(
                aggregation,
                UserNotificationProfileEntity.class,
                UserNotificationProfileProjection.class
        ).getMappedResults();

        return results.stream()
                .findFirst()
                .orElseThrow(() -> new UserNotificationProfileNotFoundException(userId));
    }
}
