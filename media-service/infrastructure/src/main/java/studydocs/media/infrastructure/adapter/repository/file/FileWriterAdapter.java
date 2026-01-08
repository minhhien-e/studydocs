package studydocs.media.infrastructure.adapter.repository.file;

import io.github.domain.aggregate.AggregateChild;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.entity.base.MongoEntity;
import io.github.infrastructure.mongo.helper.MongoHelper;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoEntityRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.media.application.port.out.storage.FileStoragePort;
import studydocs.media.domain.aggregate.File;
import studydocs.media.domain.exception.file.FileNotFoundException;
import studydocs.media.domain.repository.FileWriter;
import studydocs.media.infrastructure.mapper.FileMapper;
import studydocs.media.infrastructure.persistence.entity.FileEntity;
import studydocs.media.infrastructure.persistence.repository.MongoDataFileRepository;

import java.util.UUID;

@Repository
public class FileWriterAdapter extends AbstractAggregateMongoEntityRepository<File, FileEntity> implements FileWriter {
    private final MongoDataFileRepository mongoDataFileRepository;
    private final FileStoragePort fileStoragePort;

    public FileWriterAdapter(DomainEventSerializer domainEventSerializer, MongoTemplate mongoTemplate, MongoHelper mongoHelper, MongoDataFileRepository mongoDataFileRepository, FileStoragePort fileStoragePort) {
        super(domainEventSerializer, mongoTemplate, mongoHelper);
        this.mongoDataFileRepository = mongoDataFileRepository;
        this.fileStoragePort = fileStoragePort;
    }

    @Override
    protected AggregateChild getChildInstance(Class<? extends AggregateChild> childClass) {
        return null;
    }

    @Override
    protected void updateChildEntity(Class<? extends AggregateChild> aggregateChildClass, AggregateChild child, MongoEntity childEntity) {
    }

    @Override
    public Class<? extends FileEntity> getEntityClass() {
        return FileEntity.class;
    }

    @Override
    public File toDomainEntity(FileEntity entity) {
        return FileMapper.toDomain(entity);
    }

    @Override
    public void updateEntity(FileEntity snapshot, File domainEntity) {
        FileMapper.updateEntity(snapshot, domainEntity);
    }

    @Override
    public void deleteById(UUID id) {
        var file = mongoDataFileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException(id));
        fileStoragePort.delete(file.getPublicId(), file.getResourceType());
        mongoDataFileRepository.deleteById(id);
    }
}
