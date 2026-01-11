package studydocs.media.application.port.out.storage;

import java.io.InputStream;
import java.nio.file.Path;

public interface AssetTempStoragePort {
    Path store(InputStream content, String originalFileName);

    void delete(Path path);
}
