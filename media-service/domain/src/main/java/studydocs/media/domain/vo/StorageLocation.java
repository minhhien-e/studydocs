package studydocs.media.domain.vo;

import io.github.ddd.core.vo.ValueObject;
import studydocs.media.domain.exception.asset.InvalidStorageLocationException;

public record StorageLocation(String key, String namespace) implements ValueObject {

    public StorageLocation {
        if (key == null || key.isBlank()) {
            throw InvalidStorageLocationException.nullOrEmptyKey();
        }
        if (namespace == null || namespace.isBlank()) {
            throw InvalidStorageLocationException.nullOrEmptyNamespace();
        }
    }

    public static StorageLocation of(String key, String namespace) {
        return new StorageLocation(key, namespace);
    }
}
