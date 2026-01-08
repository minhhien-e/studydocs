package studydocs.media.application.port.out.storage;


import java.io.InputStream;
import java.util.UUID;

public interface FileStoragePort {
    void upload(InputStream inputStream, UUID fileId);
    
    void delete(String publicId, String resourceType);
}
