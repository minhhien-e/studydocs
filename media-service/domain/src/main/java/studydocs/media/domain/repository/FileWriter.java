package studydocs.media.domain.repository;

import io.github.domain.repository.AggregateRootWriter;
import studydocs.media.domain.aggregate.File;

import java.util.UUID;

public interface FileWriter extends AggregateRootWriter<File> {
    void deleteById(UUID id);
}

