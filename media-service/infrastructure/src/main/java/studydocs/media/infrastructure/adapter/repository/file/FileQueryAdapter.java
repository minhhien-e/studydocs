package studydocs.media.infrastructure.adapter.repository.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.media.application.dto.payload.GenerateDownloadUrlPayload;
import studydocs.media.application.dto.payload.GeneratePreviewUrlPayLoad;
import studydocs.media.application.dto.projection.FileProjection;
import studydocs.media.application.port.in.url.GenerateFileUrlPort;
import studydocs.media.application.port.out.repository.FileQueries;
import studydocs.media.domain.exception.file.FileNotFoundException;
import studydocs.media.infrastructure.persistence.repository.MongoDataFileRepository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FileQueryAdapter implements FileQueries {
    private final MongoDataFileRepository repository;
    private final GenerateFileUrlPort generateFileUrlPort;
    @Override
    public FileProjection getById(UUID id) {
        var entity = repository.findById(id).orElseThrow(() -> new FileNotFoundException(id));

        return FileProjection.builder()
                .id(entity.getId())
                .fileName(entity.getFileName())
                .fileSize(entity.getSize())
                .totalPage(entity.getTotalPages())
                .contentType(entity.getContentType())
                .downloadUrl(generateFileUrlPort.generateDownloadUrl(new GenerateDownloadUrlPayload(entity.getId())))
                .previewData(generateFileUrlPort.generatePreviewUrl(new GeneratePreviewUrlPayLoad(entity.getId())))
                .build();
    }
}
