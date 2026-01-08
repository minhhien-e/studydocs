package studydocs.media.application.dto.command;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;
import studydocs.media.application.dto.base.Request;

import java.util.UUID;

@Builder
public record UploadFileCommand(
        MultipartFile fileContent,
        String fileName,
        long fileSize,
        UUID uploaderId
) implements Request<UUID> {
}

