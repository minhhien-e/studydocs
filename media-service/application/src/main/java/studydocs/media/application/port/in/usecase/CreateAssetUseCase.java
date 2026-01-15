package studydocs.media.application.port.in.usecase;

import studydocs.media.application.dto.command.CreateAssetCommand;
import studydocs.media.application.port.in.usecase.base.UseCase;

import java.util.UUID;

public interface CreateAssetUseCase extends UseCase<CreateAssetCommand, UUID> {
}
