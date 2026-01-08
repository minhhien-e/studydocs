package studydocs.media.application.port.in.usecase;

import studydocs.media.application.dto.command.DeleteFileByIdCommand;
import studydocs.media.application.port.in.usecase.base.UseCase;

public interface DeleteFileByIdUseCasePort extends UseCase<DeleteFileByIdCommand, Void> {
}

