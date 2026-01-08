package studydocs.media.infrastructure.adapter.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.media.application.port.out.storage.FileStoragePort;
import studydocs.media.infrastructure.persistence.entity.FileEntity;
import studydocs.media.infrastructure.persistence.repository.MongoDataFileRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CloudinaryStorageAdapter implements FileStoragePort {

    private final Cloudinary cloudinary;
    private final MongoDataFileRepository fileRepository;

    @Override
    @SuppressWarnings("unchecked")
    public void upload(InputStream inputStream, UUID fileId) {
        try {
            byte[] fileBytes = inputStream.readAllBytes();
            var uploadMapping = (Map<String, Object>) cloudinary.uploader().upload(
                    fileBytes,
                    ObjectUtils.asMap(
                            "resource_type", "auto"
                    )
            );
            var entity = FileEntity.builder()
                    .id(fileId)
                    .publicId((String) uploadMapping.get("public_id"))
                    .resourceType((String) uploadMapping.get("resource_type"))
                    .build();
            fileRepository.save(entity);
        } catch (IOException e) {
            throw new RuntimeException("Upload failed", e);
        }
    }

    @Override
    public void delete(String publicId, String resourceType) {
        try {
            cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.asMap("resource_type", resourceType)
            );
        } catch (IOException e) {
            throw new RuntimeException("Delete failed", e);
        }
    }
}