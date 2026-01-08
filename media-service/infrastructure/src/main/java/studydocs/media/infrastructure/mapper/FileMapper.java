package studydocs.media.infrastructure.mapper;

import studydocs.media.domain.aggregate.File;
import studydocs.media.infrastructure.persistence.entity.FileEntity;

public class FileMapper {
    private FileMapper() {
    }

    public static File toDomain(FileEntity entity) {
        if (entity == null) return null;
        return File.reconstruct(entity.getId(),
                entity.getVersion(),
                entity.getUploaderId(),
                entity.getFileName(),
                entity.getSize(),
                entity.getTotalPages(),
                entity.getCreatedAt());
    }

    public static void updateEntity(FileEntity entity, File domain) {
        if (entity.getId() == null) {
            entity.setId(domain.getId());
        }
        entity.setUploaderId(domain.getUploaderId());
        entity.setFileName(domain.getFileName().getValue());
        entity.setSize(domain.getSize().getValue());
        entity.setTotalPages(domain.getTotalPages().getValue());
        entity.setCreatedAt(domain.getCreationTime().getValue());
    }
}
