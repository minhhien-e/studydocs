package studydocs.media.api.dto.request;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record UploadFileRequest(MultipartFile file) {
}
