package studydocs.media.application.port.in.usecase;

import studydocs.media.application.dto.command.CreateAssetCommand;
import studydocs.media.application.port.in.usecase.base.UseCase;

import java.util.UUID;

public interface CreateAssetUseCasePort extends UseCase<CreateAssetCommand, UUID> {
}
