package studydocs.notification.infrastructure.adapter.repository.outbox;

import io.github.domain.entity.Outbox;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import studydocs.notification.application.port.out.repository.LockingOutboxRepository;

import java.time.Instant;

@Repository
@RequiredArgsConstructor
public class LockingOutboxRepositoryAdapter implements LockingOutboxRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Outbox findAndLockNextEvent() {
        Query query = new Query(Criteria.where("status").is("PENDING")
                .orOperator(
                        Criteria.where("locked").exists(false),
                        Criteria.where("locked").is(false),
                        Criteria.where("lockedAt").lt(Instant.now().minusSeconds(60))
                ));

        Update update = new Update()
                .set("locked", true)
                .set("lockedAt", Instant.now());

        FindAndModifyOptions options = new FindAndModifyOptions()
                .returnNew(true)
                .upsert(false);

        return mongoTemplate.findAndModify(query, update, options, Outbox.class);
    }
}
