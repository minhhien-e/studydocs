package studydocs.media.application.port.in.usecase;

import studydocs.media.application.dto.command.UploadFileCommand;
import studydocs.media.application.port.in.usecase.base.UseCase;

import java.util.UUID;

public interface UploadFileUseCasePort extends UseCase<UploadFileCommand, UUID> {
}
