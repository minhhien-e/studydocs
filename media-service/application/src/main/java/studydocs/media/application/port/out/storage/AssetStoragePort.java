package studydocs.media.application.port.out.storage;

import studydocs.media.domain.vo.StorageLocation;

import java.io.File;
import java.io.InputStream;
import java.util.function.Consumer;

public interface AssetStoragePort {
    StorageLocation upload(File file, String fileName, long totalBytes, Consumer<Integer> progressCallback);

    void delete(String publicId, String resourceType);
}
