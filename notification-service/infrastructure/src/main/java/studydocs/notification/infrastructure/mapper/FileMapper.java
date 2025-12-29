package studydocs.notification.infrastructure.mapper;

import studydocs.notification.application.dto.projection.FileProjection;
import studydocs.notification.infrastructure.dto.integration.FileIntegration;

public final class FileMapper {
    private FileMapper() {}
    public static FileProjection toProjection(FileIntegration integration){
        return FileProjection.builder()
                .id(integration.id())
                .name(integration.fileName())
                .size(integration.fileSize())
                .totalPage(integration.totalPage())
                .build();
    }
}
