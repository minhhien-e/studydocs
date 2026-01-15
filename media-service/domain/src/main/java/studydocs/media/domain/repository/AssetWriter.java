package studydocs.media.domain.repository;

import io.github.ddd.core.repository.Repository;
import studydocs.media.domain.aggregate.Asset;

import java.util.UUID;

public interface AssetWriter extends Repository<Asset, UUID> {
    Asset saveAndReturn(Asset aggregate);
}
