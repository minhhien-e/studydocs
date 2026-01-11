package studydocs.media.infrastructure.adapter.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import studydocs.media.application.port.out.storage.AssetStoragePort;
import studydocs.media.domain.vo.StorageLocation;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.function.Consumer;
import studydocs.media.infrastructure.util.ProgressInputStream;

@Component
@Slf4j
@RequiredArgsConstructor
public class CloudinaryStorageAdapter implements AssetStoragePort {

    private final Cloudinary cloudinary;

    @Override
    @SuppressWarnings("unchecked")
    public StorageLocation upload(java.io.File file, String fileName, long totalBytes,
            Consumer<Integer> progressCallback) {
        try {
            log.info("Starting upload to Cloudinary for file: {}", fileName);

            // Cloudinary SDK automatically handles File uploads efficiently
            // (streaming/chunking internally).
            var uploadMapping = (Map<String, Object>) cloudinary.uploader().upload(
                    file,
                    ObjectUtils.asMap(
                            "resource_type", "auto",
                            "public_id", fileName));

            log.info("Upload to Cloudinary successful. Public ID: {}", uploadMapping.get("public_id"));

            // Invoke callback for completion
            if (progressCallback != null) {
                progressCallback.accept(100);
            }

            return StorageLocation.of(
                    (String) uploadMapping.get("public_id"),
                    (String) uploadMapping.get("resource_type"));
        } catch (IOException e) {
            log.error("Cloudinary upload failed", e);
            throw new RuntimeException("Upload failed", e);
        }
    }

    @Override
    public void delete(String publicId, String resourceType) {
        try {
            cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.asMap("resource_type", resourceType));
        } catch (IOException e) {
            throw new RuntimeException("Delete failed", e);
        }
    }

}